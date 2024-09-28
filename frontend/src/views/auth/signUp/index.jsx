import { NavLink } from "react-router-dom";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Heading,
  Icon,
  Input,
  InputGroup,
  InputRightElement,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";
import { MdOutlineRemoveRedEye } from "react-icons/md";
import { RiEyeCloseLine } from "react-icons/ri";

function SignIn() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cnpj, setCnpj] = useState("");
  const [bairro, setBairro] = useState("");
  const [cep, setCep] = useState("");
  const [uf, setUf] = useState("");
  const [cidade, setCidade] = useState("");  // Adicionado
  const [telefone, setTelefone] = useState("");
  const [endereco, setEndereco] = useState("");
  const [numero, setNumero] = useState("");
  const [buttonText, setButtonText] = useState("Cadastrar");
  const [error, setError] = useState(undefined);
  const history = useHistory();

  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const textColorDetails = useColorModeValue("navy.700", "secondaryGray.600");
  const textColorBrand = useColorModeValue("brand.500", "white");
  const brandStars = useColorModeValue("brand.500", "brand.400");
  const [show, setShow] = React.useState(false);
  const handleClick = () => setShow(!show);

  const register = async (event) => {
    if (event) {
      event.preventDefault();
    }
    if (name === "") {
      return setError("Você deve informar um nome");
    }
    if (email === "") {
      return setError("Você deve informar um email");
    }
    if (password === "") {
      return setError("Você deve informar uma senha");
    }
    if (cnpj === "") {
      return setError("Você deve informar um CNPJ");
    }
    if (bairro === "") {
      return setError("Você deve informar um bairro");
    }
    if (cep === "") {
      return setError("Você deve informar um CEP");
    }
    if (cidade === "") {  
      return setError("Você deve informar uma cidade");
    }
    if (uf === "") {
      return setError("Você deve informar um UF");
    }
    if (telefone === "") {
      return setError("Você deve informar um telefone");
    }
    if (endereco === "") {
      return setError("Você deve informar um endereço");
    }
    if (numero === "") {
      return setError("Você deve informar um número da residência");
    }

    try {
      setButtonText("Cadastrando");
      let response = await AuthApi.Register({
        cnpj: cnpj,
        name: name,
        address: endereco,
        number: numero,
        district: bairro,
        cep: cep,
        city: cidade,  // Adicionado
        uf: uf,
        fone: telefone,
        email: email,
        password: password,
      });
      console.log(response);
      if (response.data && response.data.success === false) {
        setButtonText("Cadastrar");
        return setError(response.data.msg);
      }
      return history.push("/auth/sign-in");
    } catch (err) {
      console.log(err);
      setButtonText("Cadastrar");
      if (err.response) {
        return setError(err.response.data.msg);
      }
      return setError("Houve um erro");
    }
  };

  return (
      <Flex
        maxW={{ base: "100%", md: "max-content" }}
        w="100%"
        mx={{ base: "auto", lg: "0px" }}
        me="auto"
        h="100%"
        alignItems="start"
        justifyContent="center"
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "40px", md: "14vh" }}
        flexDirection="column"
      >
        <Box me="auto">
          <Heading color={textColor} fontSize="36px" mb="10px">
            Cadastre-se
          </Heading>
          <Text
            mb="36px"
            ms="4px"
            color={textColorSecondary}
            fontWeight="400"
            fontSize="md"
          >
            Cadastre-se no sistema para acessar as informações
          </Text>
          <Flex
          zIndex="2"
          direction="column"
          w={{ base: "100%", md: "420px" }}
          maxW="100%"
          background="transparent"
          borderRadius="15px"
          mx={{ base: "auto", lg: "unset" }}
          me="auto"
          mb={{ base: "20px", md: "auto" }}
        >
          <FormControl>
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
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Nome<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Digite seu nome"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setName(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              CNPJ<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="CNPJ"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setCnpj(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Email<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              type="email"
              placeholder="seuemail@gmail.com"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setEmail(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Endereço<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Endereço"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setEndereco(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Número<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Número"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setNumero(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Bairro<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Bairro"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setBairro(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              CEP<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="CEP"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setCep(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Cidade<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Cidade"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setCidade(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              UF<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="UF"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setUf(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Telefone<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
              isRequired={true}
              variant="auth"
              fontSize="sm"
              placeholder="Telefone"
              mb="24px"
              fontWeight="500"
              size="lg"
              onChange={(event) => {
                setTelefone(event.target.value);
                setError(undefined);
              }}
            />
            <FormLabel
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              display="flex"
            >
              Senha<Text color={brandStars}>*</Text>
            </FormLabel>
            <InputGroup size="md">
              <Input
                isRequired={true}
                fontSize="sm"
                placeholder="Min. 8 caracteres"
                mb="24px"
                size="lg"
                type={show ? "text" : "password"}
                variant="auth"
                onChange={(event) => {
                  setPassword(event.target.value);
                  setError(undefined);
                }}
              />
              <InputRightElement display="flex" alignItems="center" mt="4px">
                <Icon
                  color={textColorSecondary}
                  _hover={{ cursor: "pointer" }}
                  as={show ? RiEyeCloseLine : MdOutlineRemoveRedEye}
                  onClick={handleClick}
                />
              </InputRightElement>
            </InputGroup>
            <Button
              fontSize="sm"
              variant="brand"
              fontWeight="500"
              w="100%"
              h="50"
              mb="24px"
              onClick={register}
            >
              {buttonText}
            </Button>
          </FormControl>
          <Flex
            flexDirection="column"
            justifyContent="center"
            alignItems="start"
            maxW="100%"
            mt="0px"
          >
            <Text color={textColorDetails} fontWeight="400" fontSize="14px">
              Já possui uma conta?
              <NavLink to="/auth/sign-in">
                <Text
                  color={textColorBrand}
                  as="span"
                  ms="5px"
                  fontWeight="500"
                >
                  Entrar
                </Text>
              </NavLink>
            </Text>
          </Flex>
        </Flex>
        </Box>
      </Flex>
  );
}

export default SignIn;
