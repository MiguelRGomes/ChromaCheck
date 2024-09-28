// Chakra Imports
import {
  Avatar,
  Flex,
  Icon,
  Menu,
  MenuButton,
  useColorModeValue,
} from "@chakra-ui/react";
// Custom Components
import { SidebarResponsive } from "components/sidebar/Sidebar";
import PropTypes from "prop-types";
import React from "react";
import { MdNotificationsNone} from "react-icons/md";
import routes from "routes.js";
export default function HeaderLinks(props) {
  const { secondary } = props;
  // Chakra Color Mode
  const navbarIcon = useColorModeValue("gray.400", "white");
  let menuBg = useColorModeValue("white", "navy.800");
  const shadow = useColorModeValue(
    "14px 17px 40px 4px rgba(112, 144, 176, 0.18)",
    "14px 17px 40px 4px rgba(112, 144, 176, 0.06)"
  );
  
  return (
    <Flex
      w={{ sm: "100%", md: "auto" }}
      alignItems='center'
      flexDirection='row'
      bg={menuBg}
      flexWrap={secondary ? { base: "wrap", md: "nowrap" } : "unset"}
      p='10px'
      borderRadius='30px'
      boxShadow={shadow}>
      <SidebarResponsive routes={routes} />
      <Menu>
        <MenuButton p='0px'>
          <Icon
            mt='6px'
            as={MdNotificationsNone}
            color={navbarIcon}
            w='18px'
            h='18px'
            me='10px'
          />
        </MenuButton>
      </Menu>
      <Menu>
        <MenuButton p='0px'>
          <Avatar
            _hover={{ cursor: "pointer" }}
            color='white'
            name='M G'
            bg='#051B42'
            size='sm'
            w='40px'
            h='40px'
          />
        </MenuButton>
      </Menu>
    </Flex>
  );
}

HeaderLinks.propTypes = {
  variant: PropTypes.string,
  fixed: PropTypes.bool,
  secondary: PropTypes.bool,
  onOpen: PropTypes.func,
};
