import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { getProductDetailAction } from "../features/counter/productSlice";
import { MoreProductItem } from "./MoreProductItem";
import { Loading } from "./ReuseComponent";

export default function ProductPage(props) {
  const product = useSelector((state) => state.product);
  const productDetail = product.productDetail;
  let products = product.products;
  products = products.filter((i) => i.id != props.match.params.id);
  const dispatch = useDispatch();

  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    dispatch(getProductDetailAction(props.match.params.id));
  }, []);
  if (useSelector((state) => state.product.loading)) return <Loading />;
  return (
    <div>
      <main className="my-8">
        <div className="container mx-auto px-6">
          <div className="md:flex md:items-center">
            <div className="w-full h-64 md:w-1/2 lg:h-96">
              <img
                className="h-full w-full rounded-md object-cover max-w-lg mx-auto"
                src="https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.6435-9/132411393_1379851695690197_4951753731670958909_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=V5RLt5cHeRAAX9LTLur&_nc_ht=scontent.fsgn5-4.fna&oh=542fe83f6f64f3799c828c664ce27e89&oe=614D6DC0"
                alt={productDetail.name}
              />
            </div>
            <div className="w-full max-w-lg mx-auto mt-5 md:ml-8 md:mt-0 md:w-1/2">
              <h3 className="text-gray-700 uppercase text-lg">
                {productDetail.name}
              </h3>
              <span className="text-gray-500 mt-3">
                ${productDetail.sale_price}
              </span>
              <hr className="my-3" />
              <div className="mt-2">
                <label className="text-gray-700 text-sm" htmlFor="count">
                  Count:
                </label>
                <div className="flex items-center mt-1">
                  <button
                    onClick={() => setQuantity(quantity + 1)}
                    className="text-gray-500 focus:outline-none focus:text-gray-600"
                  >
                    <svg
                      className="h-5 w-5"
                      fill="none"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </button>
                  <span className="text-gray-700 text-lg mx-2">{quantity}</span>
                  <button
                    onClick={() => {
                      if (quantity > 1) setQuantity(quantity - 1);
                    }}
                    className="text-gray-500 focus:outline-none focus:text-gray-600"
                  >
                    <svg
                      className="h-5 w-5"
                      fill="none"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </button>
                </div>
              </div>
              <div className="mt-3">
                <label className="text-gray-700 text-sm" htmlFor="count">
                  Color:
                </label>
                <div className="flex items-center mt-1">
                  <button
                    className="
                h-5
                w-5
                rounded-full
                bg-blue-600
                border-2 border-blue-200
                mr-2
                focus:outline-none
              "
                  />
                  <button
                    className="
                h-5
                w-5
                rounded-full
                bg-teal-600
                mr-2
                focus:outline-none
              "
                  />
                  <button
                    className="
                h-5
                w-5
                rounded-full
                bg-pink-600
                mr-2
                focus:outline-none
              "
                  />
                </div>
              </div>
              <div className="flex items-center mt-6">
                <button
                  className="
              px-8
              py-2
              bg-indigo-600
              text-white text-sm
              font-medium
              rounded
              hover:bg-indigo-500
              focus:outline-none focus:bg-indigo-500
            "
                >
                  Order Now
                </button>
                <button
                  className="
              mx-2
              text-gray-600
              border
              rounded-md
              p-2
              hover:bg-gray-200
              focus:outline-none
            "
                >
                  <svg
                    className="h-5 w-5"
                    fill="none"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
                  </svg>
                </button>
              </div>
            </div>
          </div>
          <div className="mt-16">
            <h3 className="text-gray-600 text-2xl font-medium">
              More Products
            </h3>
            <div
              className="
          grid
          gap-6
          grid-cols-1
          sm:grid-cols-2
          lg:grid-cols-3
          xl:grid-cols-4
          mt-6
        "
            >
              {products.map((i) => (
                <MoreProductItem
                  key={i.id}
                  name={i.name}
                  sale_price={i.sale_price}
                />
              ))}
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
