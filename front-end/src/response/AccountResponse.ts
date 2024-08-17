export default interface AccountResponse {
  email: string;
  first_name: string;
  last_name: string;
  role: 'USER' | 'ADMIN'; // Giả định rằng role chỉ có thể là 'USER' hoặc 'ADMIN'
}