import { z } from "zod";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { SubmitHandler, useForm } from "react-hook-form";
import { createAccount } from "../service/accountService";
import { toast } from "react-toastify";
import { useAppSelector } from "../redux/hooks";
const schema = z
  .object({
    email: z.string().email(),
    password: z.string().min(8).max(20),
    confirm_password: z.string().min(8).max(20),
    first_name: z.string().min(2).max(50),
    last_name: z.string().min(2).max(50),
  })
  .refine((data) => data.password === data.confirm_password, {
    message: "Passwords must match",
    path: ["confirmPassword"], // đặt thông báo lỗi tại trường confirmPassword
  });
export type AccountRequest = z.infer<typeof schema>;
type Props={
  showLogin: ()=>void
}
const RegisterComponent = ({showLogin}:Props) => {
  const socialAccount=useAppSelector((state)=>state.socialAccount);
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, errors },
  } = useForm<AccountRequest>();
  const handleSubmitCreate: SubmitHandler<AccountRequest> = (data) => {
    createAccount(data, socialAccount.social_account).then((d) => {
      if (d.success) {
        toast.success("Successfully");
        showLogin();
      } else {
        toast.error(d.message);
      }
    });
  };
  return (
    <>
    <Form onSubmit={handleSubmit(handleSubmitCreate)}>
      <Form.Group className="mb-3" controlId="formEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control
          type="email"
          {...register("email")}
          isInvalid={!!errors.email}
        />
        <Form.Control.Feedback type="invalid">
          {errors.email?.message}
        </Form.Control.Feedback>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          {...register("password")}
          isInvalid={!!errors.password}
        />
        <Form.Control.Feedback type="invalid">
          {errors.password?.message}
        </Form.Control.Feedback>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formConfirmPassword">
        <Form.Label>Confirm Password</Form.Label>
        <Form.Control
          type="password"
          {...register("confirm_password")}
          isInvalid={!!errors.confirm_password}
        />
        <Form.Control.Feedback type="invalid">
          {errors.confirm_password?.message}
        </Form.Control.Feedback>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formFirstName">
        <Form.Label>First Name</Form.Label>
        <Form.Control
          type="text"
          {...register("first_name")}
          isInvalid={!!errors.first_name}
        />
        <Form.Control.Feedback type="invalid">
          {errors.first_name?.message}
        </Form.Control.Feedback>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formLastName">
        <Form.Label>Last Name</Form.Label>
        <Form.Control
          type="text"
          {...register("last_name")}
          isInvalid={!!errors.last_name}
        />
        <Form.Control.Feedback type="invalid">
          {errors.last_name?.message}
        </Form.Control.Feedback>
      </Form.Group>

      <Button variant="primary" type="submit" disabled={isSubmitting}>
        Submit
      </Button>
    </Form>
      <button onClick={()=>showLogin()}>Login</button>
    </>
  );
};

export default RegisterComponent;
