import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { NavLink } from "react-router-dom";

// Chakra imports
import {
  Box,
  Button,
  Checkbox,
  Flex,
  FormControl,
  FormLabel,
  Icon,
  Input,
  InputGroup,
  InputRightElement,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
// Custom components
import DefaultAuth from "layouts/auth/Default";
// Assets
import illustration from "assets/img/auth/auth.png";
import { MdOutlineRemoveRedEye } from "react-icons/md";
import { RiEyeCloseLine } from "react-icons/ri";
import { useAuth } from "../../../auth-context/auth.context";
import AuthApi from "../../../api/auth";

function SignIn() {
  const [cnpj, setCNPJ] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(undefined);
  const [buttonText, setButtonText] = useState("Sign in");
  const history = useHistory();
  const { setUser } = useAuth();
  const { user } = useAuth();

  // Chakra color mode
  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const textColorDetails = useColorModeValue("navy.700", "secondaryGray.600");
  const textColorBrand = useColorModeValue("brand.500", "white");
  const brandStars = useColorModeValue("brand.500", "brand.400");
  const [show, setShow] = useState(false);

  const handleClick = () => setShow(!show);

  const login = async (event) => {
    if (event) {
      event.preventDefault();
    }
    if (user && user.token) {
      return history.push("/admin/dashboards");
    }
    if (cnpj === "") {
      return setError("Informe o CNPJ");
    }
    if (password === "") {
      return setError("Informe a senha");
    }
    setButtonText("Signing in");
    try {
      let response = await AuthApi.Login(cnpj, password);

      if (response.data && response.data.success === false) {
        setButtonText("Sign in");
        return setError(response.data.msg);
      }
      const userId = response.data.id;
      localStorage.setItem('userId', userId);
      console.log(response);
      console.log(userId);
      return setProfile(response);
    } catch (err) {
      console.log("Error details:", err);
      if (err.response && err.response.data && err.response.data.message) {
        return setError(err.response.data.message);
      }
      return setError("CNPJ ou Senha incorreta. Por favor verifique!");
    }
  };

  const setProfile = async (response) => {
    let user = { ...response.data.id }; // Mantém o ID do usuário
    user = JSON.stringify(user);
    setUser(user);
    localStorage.setItem("user", user);
    return history.push("/dashboards");
  };

  return (
    <DefaultAuth illustrationBackground={illustration} image={illustration}>
      <Flex
        maxW={{ base: "100%", md: "max-content" }}
        w='100%'
        mx='auto'
        h='100%'
        alignItems='center' // Alterado para 'center' para centralizar verticalmente
        justifyContent='center' // Para centralizar horizontalmente
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "40px", md: "14vh" }}
        flexDirection='column'>
        <Box me='auto'>
          <Text
            mb='36px'
            ms='4px'
            color={textColorSecondary}
            fontWeight='400'
            fontSize='md'>
          </Text>
        </Box>

        <FormControl
          zIndex='2'
          direction='column'
          w={{ base: "100%", md: "420px" }}
          maxW='100%'
          background='transparent'
          borderRadius='15px'
          mx='auto'
          mb={{ base: "20px", md: "auto" }}>
          <h4
            style={{
              fontSize: ".9em",
              color: "red",
              textAlign: "center",
              fontWeight: 400,
              transition: ".2s all",
            }}
          >
            {error}
          </h4>
          <FormLabel
            display='flex'
            ms='4px'
            fontSize='sm'
            fontWeight='500'
            color={textColor}
            mb='8px'>
            CNPJ<Text color={brandStars}>*</Text>
          </FormLabel>
          <Input
            isRequired={true}
            variant='auth'
            fontSize='sm'
            ms={{ base: "0px", md: "0px" }}
            type='cnpj'
            placeholder='CNPJ'
            mb='24px'
            defaultValue={cnpj}
            fontWeight='500'
            size='lg'
            onChange={(event) => {
              setCNPJ(event.target.value);
              setError(undefined);
            }}
          />
          <FormLabel
            ms='4px'
            fontSize='sm'
            fontWeight='500'
            color={textColor}
            display='flex'>
            Senha<Text color={brandStars}>*</Text>
          </FormLabel>
          <InputGroup size='md'>
            <Input
              isRequired={true}
              fontSize='sm'
              placeholder='Min. 8 characters'
              mb='24px'
              size='lg'
              defaultValue={password}
              type={show ? "text" : "password"}
              variant='auth'
              onChange={(event) => {
                setPassword(event.target.value);
                setError(undefined);
              }}
            />
            <InputRightElement display='flex' alignItems='center' mt='4px'>
              <Icon
                color={textColorSecondary}
                _hover={{ cursor: "pointer" }}
                as={show ? RiEyeCloseLine : MdOutlineRemoveRedEye}
                onClick={handleClick}
              />
            </InputRightElement>
          </InputGroup>
          <Flex justifyContent='space-between' align='center' mb='24px'>
            <FormControl display='flex' alignItems='center'>
              <Checkbox
                id='remember-login'
                colorScheme='brandScheme'
                me='10px'
              />
              <FormLabel
                htmlFor='remember-login'
                mb='0'
                fontWeight='normal'
                color={textColor}
                fontSize='sm'>
                Mantenha-me conectado
              </FormLabel>
            </FormControl>
            <NavLink to='/auth/forgot-password'>
              <Text
                color={textColorBrand}
                fontSize='sm'
                w='124px'
                fontWeight='500'>
                Esqueceu sua senha?
              </Text>
            </NavLink>
          </Flex>
          <Button
            fontSize='sm'
            variant='brand'
            fontWeight='500'
            w='100%'
            h='50'
            mb='24px'
            onClick={login}>
            Entrar
          </Button>
          <Flex
            flexDirection='column'
            justifyContent='center'
            alignItems='center' // Alinhado ao centro
            maxW='100%'
            mt='0px'>
            <Text color={textColorDetails} fontWeight='400' fontSize='14px'>
              Não cadastrado ainda?
              <NavLink to='/auth/sign-up'>
                <Text
                  color={textColorBrand}
                  as='span'
                  ms='5px'
                  fontWeight='500'>
                  Crie a sua conta aqui
                </Text>
              </NavLink>
            </Text>
          </Flex>
        </FormControl>
      </Flex>
    </DefaultAuth>
  );
}

export default SignIn;
