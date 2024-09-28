import { mode } from "@chakra-ui/theme-tools";
export const globalStyles = {
  colors: {
    brand: {
      100: "#059bef", // azul claro
      200: "#17f1fd", // azul médio
      300: "#17f1fd", // azul médio
      400: "#17f1fd", // azul médio
      500: "#059bef", // azul claro
      600: "#17f1fd", // azul médio
      700: "#041d46", // azul escuro
      800: "#041d46", // azul escuro
      900: "#041d46", // azul escuro
    },
    brandScheme: {
      100: "#059bef", // azul claro
      200: "#17f1fd", // azul médio
      300: "#17f1fd", // azul médio
      400: "#17f1fd", // azul médio
      500: "#059bef", // azul claro
      600: "#17f1fd", // azul médio
      700: "#041d46", // azul escuro
      800: "#041d46", // azul escuro
      900: "#041d46", // azul escuro
    },
    brandTabs: {
      100: "#E9E3FF",
      200: "#422AFB",
      300: "#422AFB",
      400: "#422AFB",
      500: "#422AFB",
      600: "#3311DB",
      700: "#02044A",
      800: "#190793",
      900: "#02044A",
    },
    secondaryGray: {
      100: "#E0E5F2",
      200: "#E1E9F8",
      300: "#F4F7FE",
      400: "#E9EDF7",
      500: "#8F9BBA",
      600: "#A3AED0",
      700: "#707EAE",
      800: "#707EAE",
      900: "#1B2559",
    },
    red: {
      100: "#FEEFEE",
      500: "#EE5D50",
      600: "#FF0000",
    },
    red2: {
      100: "#FF0000",
      500: "#FF0000",
      600: "#FF0000",
    },
    blue: {
      50: "#EFF4FB",
      500: "#3965FF",
    },
    orange: {
      100: "#FFF6DA",
      500: "#FFB547",
    },
    green: {
      100: "#E6FAF5",
      500: "#01B574", // mantido como verde
    },
    navy: {
      50: "#d0dcfb",
      100: "#aac0fe",
      200: "#a3b9f8",
      300: "#728fea",
      400: "#3652ba",
      500: "#1b3bbb",
      600: "#24388a",
      700: "#1B254B",
      800: "#111c44",
      900: "#0b1437",
    },
    gray: {
      100: "#FAFCFE",
    },
  },
  styles: {
    global: (props) => ({
      body: {
        overflowX: "hidden",
        bg: mode("secondaryGray.300", "navy.900")(props),
        fontFamily: "DM Sans",
        letterSpacing: "-0.5px",
      },
      input: {
        color: "gray.700",
      },
      html: {
        fontFamily: "DM Sans",
      },
    }),
  },
};
