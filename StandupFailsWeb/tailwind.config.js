const Colors = require("./src/utils/colors")

module.exports = {
    mode: "jit",
    purge: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
    darkMode: false, // or 'media' or 'class'
    theme: {
        colors: {
            transparent: Colors.transparent,
            background: Colors.background,
            separator: Colors.separator,
            black: Colors.black,
            white: Colors.white,
            purple: Colors.purple,
            blue: Colors.blue,
            red: Colors.red,
        },
        fontFamily: {
            "sans": ["Lato", "sans-serif"],
            "caveat": ["Caveat", "sans-serif"]
        }
    },
    variants: {
        extend: {},
    },
    plugins: [],
}
