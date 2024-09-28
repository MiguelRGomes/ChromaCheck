import React, { useState } from "react";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import AuthApi from "../../../api/auth";
import { useHistory } from "react-router-dom";

export default function RegisterPrice() {
  const [name, setName] = useState("");
  const [pricePerSquareMeter, setPricePerSquareMeter] = useState(""); // Estado para preço por metro quadrado
  const [fixedPrice, setFixedPrice] = useState(""); // Estado para preço fixo
  const [error, setError] = useState(undefined);
  const [buttonText, setButtonText] = useState("Cadastrar");

  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const bgColor = useColorModeValue("white", "gray.700");

  const history = useHistory();

  const register = async (event) => {
    if (event) event.preventDefault();
    try {
      setButtonText("Cadastrando");

      const userId = JSON.parse(localStorage.getItem("userId"));
      if (!userId) {
        return setError("Usuário não está logado.");
      }

      const payload = {
        companyEntity: { id: userId },
        name: name,
        square_meter: parseFloat(pricePerSquareMeter), // Adicionado preço por metro quadrado ao payload
        fixed_price: parseFloat(fixedPrice), // Adicionado preço fixo ao payload
      };

      let response = await AuthApi.RegisterPrice(payload); // Chamada de API para cadastrar preço

      if (response.data && response.data.success === false) {
        resetForm();
        setError(response.data.msg);
      } else {
        resetForm();
        setError(undefined);
        setButtonText("Cadastrar");
        history.push("/admin/price"); // Redireciona para a lista de preços
      }
    } catch (err) {
      setButtonText("Cadastrar");
      console.log(err);
      if (err.response) {
        return setError(err.response.data.msg);
      }
      return setError("Houve um erro");
    }
  };

  const resetForm = () => {
    setName("");
    setPricePerSquareMeter(""); // Limpar preço por metro quadrado ao resetar o formulário
    setFixedPrice(""); // Limpar preço fixo ao resetar o formulário
    setError(undefined);
  };

  const handleClose = () => {
    history.push("/admin/price"); // Redireciona para a lista de preços
  };

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
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
        mt={{ base: "15px", md: "5vh" }}
        flexDirection="column"
      >
        <Box me="auto">
          <Text
            mb="36px"
            ms="4px"
            color={textColorSecondary}
            fontWeight="400"
            fontSize="md"
          >
            Cadastre o preço abaixo
          </Text>
          <Box
            bg={bgColor}
            p="24px"
            borderRadius="15px"
            boxShadow="lg"
            w={{ base: "100%", md: "420px" }}
          >
            <Flex
              zIndex="2"
              direction="column"
              w="100%"
              maxW="100%"
              background="transparent"
              borderRadius="15px"
              mx="auto"
              mb={{ base: "20px", md: "auto" }}
            >
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
              <FormControl>
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Nome do Preço
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o nome do preço"
                  mb="24px"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Preço por Metro Quadrado
                </FormLabel>
                <Input
                  type="number"
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o preço por metro quadrado"
                  mb="24px"
                  value={pricePerSquareMeter}
                  onChange={(e) => setPricePerSquareMeter(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Preço Fixo
                </FormLabel>
                <Input
                  type="number"
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o preço fixo"
                  mb="24px"
                  value={fixedPrice}
                  onChange={(e) => setFixedPrice(e.target.value)}
                />
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    h="50"
                    mb="24px"
                    onClick={handleClose}
                  >
                    Fechar
                  </Button>
                  <Button
                    fontSize="sm"
                    variant="brand"
                    fontWeight="500"
                    w="48%"
                    h="50"
                    mb="24px"
                    onClick={register}
                  >
                    {buttonText}
                  </Button>
                </Flex>
              </FormControl>
            </Flex>
          </Box>
        </Box>
      </Flex>
    </Box>
  );
}
