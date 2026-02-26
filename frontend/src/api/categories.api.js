import { http } from "./http";

export const fetchCategories = async () => {
  const res = await http.get("/api/public/categories");
  return res.data;
};