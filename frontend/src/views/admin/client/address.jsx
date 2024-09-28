import React, { useState } from "react";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Select,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import { useParams, useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";

const ufOptions = [
  { value: "AC", label: "Acre" },
  { value: "AL", label: "Alagoas" },
  { value: "AP", label: "Amapá" },
  { value: "AM", label: "Amazonas" },
  { value: "BA", label: "Bahia" },
  { value: "CE", label: "Ceará" },
  { value: "DF", label: "Distrito Federal" },
  { value: "ES", label: "Espírito Santo" },
  { value: "GO", label: "Goiás" },
  { value: "MA", label: "Maranhão" },
  { value: "MT", label: "Mato Grosso" },
  { value: "MS", label: "Mato Grosso do Sul" },
  { value: "MG", label: "Minas Gerais" },
  { value: "PA", label: "Pará" },
  { value: "PB", label: "Paraíba" },
  { value: "PR", label: "Paraná" },
  { value: "PE", label: "Pernambuco" },
  { value: "PI", label: "Piauí" },
  { value: "RJ", label: "Rio de Janeiro" },
  { value: "RN", label: "Rio Grande do Norte" },
  { value: "RS", label: "Rio Grande do Sul" },
  { value: "RO", label: "Rondônia" },
  { value: "RR", label: "Roraima" },
  { value: "SC", label: "Santa Catarina" },
  { value: "SP", label: "São Paulo" },
  { value: "SE", label: "Sergipe" },
  { value: "TO", label: "Tocantins" },
];

export default function RegisterAddress() {
  const [address, setAddress] = useState("");
  const [number, setNumber] = useState("");
  const [neighborhood, setNeighborhood] = useState("");
  const [cep, setCep] = useState("");
  const [city, setCity] = useState("");
  const [uf, setUf] = useState("");
  const [error, setError] = useState(undefined);
  const [buttonText, setButtonText] = useState("Cadastrar");

  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const bgColor = useColorModeValue("white", "gray.700");

  const history = useHistory();

  const { id } = useParams(); // Recupera o id da pessoa a partir da URL

  const registerAddress = async (event) => {
    if (event) event.preventDefault();
    try {
      setButtonText("Cadastrando");

      const userId = JSON.parse(localStorage.getItem("userId"));
      if (!userId) {
        return setError("Usuário não está logado.");
      }

      const payload = {
        personEntity: {
          id: id, // Usa o id da pessoa obtido da URL
        },
        adress: address,
        number: number,
        district: neighborhood, // Mudei para 'district' conforme sua estrutura
        city: city,
        cep: cep,
        uf: uf,
      };

      let response = await AuthApi.RegisterAddress(payload);

      if (response.data && response.data.success === false) {
        resetForm();
        setError(response.data.msg);
      } else {
        resetForm();
        setError(undefined);
        setButtonText("Cadastrar");
        history.push(`/admin/editclient/${id}`);
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
    setAddress("");
    setNumber("");
    setNeighborhood("");
    setCep("");
    setCity("");
    setUf("");
    setError(undefined);
  };

  const handleClose = () => {
    history.push(`/admin/editclient/${id}`);
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
            Cadastre o endereço abaixo
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
                  Endereço
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o endereço"
                  mb="24px"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Número
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o número"
                  mb="24px"
                  value={number}
                  onChange={(e) => setNumber(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Bairro
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o bairro"
                  mb="24px"
                  value={neighborhood}
                  onChange={(e) => setNeighborhood(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  CEP
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite o CEP"
                  mb="24px"
                  value={cep}
                  onChange={(e) => setCep(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Cidade
                </FormLabel>
                <Input
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  placeholder="Digite a cidade"
                  mb="24px"
                  value={city}
                  onChange={(e) => setCity(e.target.value)}
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  UF
                </FormLabel>
                <Select
                  isRequired
                  variant="auth"
                  fontSize="sm"
                  mb="24px"
                  value={uf}
                  onChange={(e) => setUf(e.target.value)}
                >
                  <option value="">Selecione o UF</option>
                  {ufOptions.map((uf) => (
                    <option key={uf.value} value={uf.value}>
                      {uf.label}
                    </option>
                  ))}
                </Select>
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    h="50px"
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
                    h="50px"
                    mb="24px"
                    onClick={registerAddress}
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
