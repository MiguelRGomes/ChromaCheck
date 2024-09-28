import React from "react";
import ReactDOM from "react-dom";
import "assets/css/App.css";
import { HashRouter, Route, Switch, Redirect } from "react-router-dom";
import AuthLayout from "layouts/auth";
import AdminLayout from "layouts/admin";
import { ChakraProvider } from "@chakra-ui/react";
import theme from "theme/theme";
import { AuthProvider } from "./auth-context/auth.context";

let user = localStorage.getItem("user");
user = JSON.parse(user);
ReactDOM.render(
  <ChakraProvider theme={theme}>
    <AuthProvider userData={user}>
      <React.StrictMode>
        <HashRouter>
          <Switch>
            <Route path={`/auth`} component={AuthLayout} />
            <Route path={`/admin`} component={AdminLayout} />
            <Redirect from='/' to='/admin/dashboards' />
          </Switch>
        </HashRouter>
      </React.StrictMode>
    </AuthProvider>
  </ChakraProvider>,
  document.getElementById("root")
);
