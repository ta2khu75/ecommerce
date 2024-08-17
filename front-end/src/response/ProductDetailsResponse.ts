export default interface ProductDetailsResponse {
  id: number;
  name: string;
  price: number;
  remaining: number;
  image: string;
  active: boolean;
  description: string;
  category: string;
  product_images: string[];
}
