import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import Item from "./Item";

import { getProductsAction } from "../features/counter/productSlice";

export function HomePage() {
  const products = useSelector((state) => state.product.products);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getProductsAction(0));
  }, []);
  return (
    <div className="flex flex-wrap">
      {products &&
        products.map((i) => {
          return (
            <Item
              key={i.id}
              id={i.id}
              name={i.name}
              raw_price={i.raw_price}
              sale_price={i.sale_price}
              short_des={i.short_des}
            />
          );
        })}
    </div>
  );
}
