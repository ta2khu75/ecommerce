import { login, loginGoogle } from "../service/authenticationService";
import { SubmitHandler, useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";
import { setAccount } from "../redux/slice/accountSlice";
import { CredentialResponse, GoogleLogin } from "@react-oauth/google";
import { setSocialAccount } from "../redux/slice/socialAccountSlice";
const schema = z.object({
  email: z.string().email(),
  password: z.string().min(3),
});
export type AuthenticationRequest = z.infer<typeof schema>;
type Props = {
  showRegister: () => void;
};
const LoginComponent = ({ showRegister }: Props) => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, errors },
  } = useForm<AuthenticationRequest>({ resolver: zodResolver(schema) });

  const handleLogin: SubmitHandler<AuthenticationRequest> = async (data) => {
    const result = await login(data);
    if (result.success && result.data) {
      dispatch(setAccount(result.data));
      toast.info("Login successfully");
    } else {
      toast.error(result.message);
    }
  };
  const handleLoginGoogleSuccess = async (data: CredentialResponse) => {
    const result = await loginGoogle(data.credential, data.clientId);
    if (result.success && result.data) {
      if (result?.data?.social_account) {
        dispatch(setSocialAccount(result.data));
        showRegister();
      } else if (result?.data?.account) {
        dispatch(setAccount(result.data.account));
        toast.success("Login successfully");
      }
    }else{
      toast.error(result.message);
    }
  };

  return (
    <div className="card-body p-3 p-md-4 p-xl-5">
      <form action="#!" onSubmit={handleSubmit(handleLogin)}>
        <div className="row gy-3 overflow-hidden">
          <div className="col-12">
            <div className="form-floating mb-3">
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="name@example.com"
                {...register("email")}
              />
              <label htmlFor="email" className="form-label">
                Email
              </label>
            </div>
            <p className="text-danger">{errors.email?.message}</p>
          </div>
          <div className="col-12">
            <div className="form-floating mb-3">
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder="Password"
                required
                {...register("password")}
              />
              <label htmlFor="password" className="form-label">
                Password
              </label>
            </div>
            <p className="text-danger">{errors.password?.message}</p>
          </div>
          <div className="col-12">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                name="remember_me"
                id="remember_me"
              />
              <label
                className="form-check-label text-secondary"
                htmlFor="remember_me"
              >
                Keep me logged in
              </label>
            </div>
          </div>
          <div className="col-12">
            <div className="d-grid">
              <button
                className="btn btn-primary btn-lg"
                disabled={isSubmitting}
                type="submit"
              >
                {isSubmitting ? "loading" : "Log in now"}
              </button>
              <GoogleLogin
                onSuccess={(data) => handleLoginGoogleSuccess(data)}
                onError={() => {
                  toast.error("Login failed");
                }}
              />
              <button onClick={() => showRegister()}>register</button>
            </div>
          </div>
        </div>
      </form>
      <div className="row">
        <div className="col-12">
          <div className="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end mt-4">
            <a href="#!">Forgot password</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;
