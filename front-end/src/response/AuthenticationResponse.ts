import AccountResponse from "./AccountResponse";

export default interface AuthenticationResponse {
  account?: AccountResponse;
  token?: string;
  authentication: boolean;
}
