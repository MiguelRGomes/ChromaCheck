import React from "react";

import { Icon } from "@chakra-ui/react";
import {
  MdPerson,
  MdHome,
  MdLogout,
  MdGroup,
  MdBuild,
  MdAttachMoney,
  MdShoppingCart,
  MdOutlineReceiptLong,
  MdLogin,
} from "react-icons/md";

// Admin Imports
import MainDashboard from "views/admin/default";
import Profile from "views/admin/profile";
import Client from "views/admin/client/client";
import Client2 from "views/admin/client/index";
import Client3 from "views/admin/client/editclient";
import Client4 from "views/admin/client/address";
import Budget from "views/admin/budget/budget";
import Budget2 from "views/admin/budget/index";
import Budget3 from "views/admin/budget/editbudget";
import Budget4 from "views/admin/budget/item";
import Budget5 from "views/admin/budget/service";
import Service from "views/admin/service/service";
import Service2 from "views/admin/service/index";
import Product from "views/admin/product/product";
import Product2 from "views/admin/product/index";
import Prices from "views/admin/price/price";
import Prices2 from "views/admin/price/index";

import SignIn from "views/auth/signIn/index.jsx";

const routes = [
  {
    name: "Dashboard",
    layout: "/admin",
    path: "/default",
    icon: <Icon as={MdHome} width='20px' height='20px' color='inherit' />,
    component: MainDashboard,
  },
  {
    name: "Cliente/Loja Parceira",
    layout: "/admin",
    path: "/client",
    icon: <Icon as={MdGroup} width='20px' height='20px' color='inherit' />,
    component: Client,
  },
  {
    name: "Cliente/Loja Parceira",
    layout: "/admin",
    path: "/registerclient",
    icon: <Icon as={MdGroup} width='20px' height='20px' color='inherit' />,
    component: Client2,
    hide: true
  },
  {
    name: "Cliente/Loja Parceira",
    layout: "/admin",
    path: "/editclient/:id",
    icon: <Icon as={MdGroup} width='20px' height='20px' color='inherit' />,
    component: Client3,
    hide: true
  },
  {
    name: "Cliente/Loja Parceira",
    layout: "/admin",
    path: "/:id/address",
    icon: <Icon as={MdGroup} width='20px' height='20px' color='inherit' />,
    component: Client4,
    hide: true
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/budget",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget,
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/registerbudget",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget2,
    hide: true
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/editbudget/:id",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget3,
    hide: true
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/:id/budget-item",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget4,
    hide: true
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/:id/budget-service",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget5,
    hide: true
  },
  {
    name: "Orçamento",
    layout: "/admin",
    path: "/:id/budget-product",
    icon: <Icon as={MdOutlineReceiptLong} width='20px' height='20px' color='inherit' />,
    component: Budget4,
    hide: true
  },
  {
    name: "Serviço",
    layout: "/admin",
    path: "/service",
    icon: <Icon as={MdBuild} width='20px' height='20px' color='inherit' />,
    component: Service,
  },
  {
    name: "Serviço",
    layout: "/admin",
    path: "/registerservice",
    icon: <Icon as={MdBuild} width='20px' height='20px' color='inherit' />,
    component: Service2,
    hide: true
  },
  {
    name: "Preço",
    layout: "/admin",
    path: "/price",
    icon: <Icon as={MdAttachMoney} width='20px' height='20px' color='inherit' />,
    component: Prices,
  },
  {
    name: "Preço",
    layout: "/admin",
    path: "/registerprice",
    icon: <Icon as={MdAttachMoney} width='20px' height='20px' color='inherit' />,
    component: Prices2,
    hide: true
  },
  {
    name: "Produto",
    layout: "/admin",
    path: "/product",
    icon: <Icon as={MdShoppingCart} width='20px' height='20px' color='inherit' />,
    component: Product,
  },
  {
    name: "Produto",
    layout: "/admin",
    path: "/registerproduct",
    icon: <Icon as={MdShoppingCart} width='20px' height='20px' color='inherit' />,
    component: Product2,
    hide: true
  },
  {
    name: "Perfil",
    layout: "/admin",
    path: "/profile",
    icon: <Icon as={MdPerson} width='20px' height='20px' color='inherit' />,
    component: Profile,
  },
  {
    name: "Entrar",
    layout: "/auth",
    path: "/sign-in",
    icon: (
      <Icon as={MdLogin} width='16px' height='16px' color='inherit' />
    ),
    component: SignIn,
    hide: true
  },
];

export const Logout = [
  {
    name: "Sair",
    layout: "/auth",
    path: "/sign-out",
    icon: (
      <Icon as={MdLogout} width='16px' height='16px' color='inherit' />
    ),
    component: SignIn,
  }
];
export default routes;
