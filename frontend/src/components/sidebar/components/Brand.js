import React from "react";

// Chakra imports
import { Flex, useColorModeValue } from "@chakra-ui/react";
import { HSeparator } from "components/separator/Separator";
import authLogo from "assets/img/auth/auth.png"

export function SidebarBrand() {
  // Chakra color mode
  let logoColor = useColorModeValue("navy.700", "white");

  return (
    <Flex align="center" direction="column">
      <img src={authLogo} alt="Logo" style={{ height: '175px', width: '175px', margin: '5px 0', color: logoColor }} />
      <HSeparator mb="20px" />
    </Flex>
  );
}

export default SidebarBrand;
