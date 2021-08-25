module.exports = {
  purge: ["./src/**/*.{js,jsx,ts,tsx}", "./public/index.html"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      backgroundImage: (theme) => ({
        "login-page": "url('/src/assets/img/bd-login.jpg')",
      }),
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
