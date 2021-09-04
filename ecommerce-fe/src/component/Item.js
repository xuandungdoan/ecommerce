import React from "react";

export default function Item(name, raw_price, sale_price) {
  return (
    <div className="w-full sm:w-1/2 md:w-1/3 lg:w-1/4 p-4">
      <div className="bg-white shadow-lg hover:shadow-xl rounded-lg ">
        <div
          className="bg-gray-400 h-64 rounded-t-lg p-4 bg-no-repeat bg-center bg-cover"
          style={{
            backgroundImage:
              "url(https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.6435-9/132411393_1379851695690197_4951753731670958909_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=V5RLt5cHeRAAX9LTLur&_nc_ht=scontent.fsgn5-4.fna&oh=542fe83f6f64f3799c828c664ce27e89&oe=614D6DC0)",
          }}
        >
          <div className="text-right">
            <button
              className="text-gray-300 hover:text-pink-500 p-2 rounded-full"
              style={{ background: "rgba(0,0,0,0.3)" }}
            >
              <svg className="w-6 h-6" viewBox="0 0 24 24">
                <path
                  fill="currentColor"
                  d="M12,21.35L10.55,20.03C5.4,15.36 2,12.27 2,8.5C2,5.41 4.42,3 7.5,3C9.24,3 10.91,3.81 12,5.08C13.09,3.81 14.76,3 16.5,3C19.58,3 22,5.41 22,8.5C22,12.27 18.6,15.36 13.45,20.03L12,21.35Z"
                />
              </svg>
            </button>
          </div>
        </div>
        <div className="flex justify-between items-start px-2 pt-2">
          <div className="p-2 flex-grow">
            <h1 className="font-medium text-xl font-poppins">Lan Anh</h1>
            <p className="text-gray-500 font-nunito">dumb girl</p>
          </div>
          <div className="p-2 text-right">
            <div className="text-teal-500 font-semibold text-lg font-poppins">
              $1
            </div>
            <div className="text-xs text-gray-500 line-through font-poppins">
              $100
            </div>
          </div>
        </div>
        <div className="flex justify-center items-center px-2 pb-2 flex-col xl:flex-row">
          <div className="w-1/2 p-2">
            <button className="text-xs xl:text-sm block w-full bg-teal-500 hover:bg-teal-600 text-black border-2 border-teal-500 hover:border-teal-600 px-3 py-2 rounded uppercase font-poppins font-medium flex  justify-center items-center">
              <svg viewBox="0 0 24 24" className="inline mr-1 w-4 h-4">
                <path
                  fill="currentColor"
                  d="M12,9A3,3 0 0,0 9,12A3,3 0 0,0 12,15A3,3 0 0,0 15,12A3,3 0 0,0 12,9M12,17A5,5 0 0,1 7,12A5,5 0 0,1 12,7A5,5 0 0,1 17,12A5,5 0 0,1 12,17M12,4.5C7,4.5 2.73,7.61 1,12C2.73,16.39 7,19.5 12,19.5C17,19.5 21.27,16.39 23,12C21.27,7.61 17,4.5 12,4.5Z"
                />
              </svg>{" "}
              Details
            </button>
          </div>
          <div className="w-1/2 p-2">
            <button
              className="flex-shrink-0 text-xs xl:text-sm block w-full bg-white 
    hover:bg-gray-100 text-teal-500 border-2 border-teal-500 px-3 py-2 rounded uppercase font-poppins font-medium"
            >
              Add to cart
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
