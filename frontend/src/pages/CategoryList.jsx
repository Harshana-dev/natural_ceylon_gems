import React, { useEffect, useState } from "react";
import { fetchCategories } from "../api/categories.api";

export default function CategoryList() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchCategories()
      .then(setItems)
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="max-w-3xl mx-auto p-4">
      <h1 className="text-2xl font-semibold">Gem Categories</h1>

      {loading && <p className="mt-4">Loading...</p>}

      {!loading && (
        <ul className="mt-4 space-y-2">
          {items.map((c) => (
            <li
              key={c.id}
              className="p-3 rounded-xl shadow bg-white flex justify-between"
            >
              <span>{c.name}</span>
              <span className="text-sm opacity-60">{c.slug}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}