import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Select,
  Button,
  Text,
  useColorModeValue,
  Grid,
  GridItem,
  IconButton,
} from "@chakra-ui/react";
import { useParams, useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";
import { DeleteIcon} from "@chakra-ui/icons"; // Importar ícones de deletar e editar do Chakra UI

export default function EditClientForm() {
  const { id } = useParams(); // Obtém o ID do cliente da URL
  const companyId = JSON.parse(localStorage.getItem("userId")); // Recupera o userId do localStorage
  const [clientData, setClientData] = useState({
    name: "",
    cpf_cnpj: "",
    fone: "",
    email: "",
    type: "C", // Defina o valor padrão se necessário
  });
  const [addresses, setAddresses] = useState([]); // Estado para armazenar os endereços
  const [error, setError] = useState(undefined);
  const [buttonText, setButtonText] = useState("Salvar");

  const textColor = useColorModeValue("navy.700", "white");
  const bgColor = useColorModeValue("white", "gray.700"); // Cor de fundo

  const history = useHistory();

  useEffect(() => {
    const fetchClientData = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetPeopleId(id); // Passa o id do cliente
        setClientData(response.data); // Atualiza o estado com os dados recebidos
      } catch (err) {
        setError("Erro ao buscar os dados do cliente.");
        console.error("Erro ao buscar os dados do cliente:", err);
      }
    };

    const fetchAddresses = async () => {
      try {
        const response = await AuthApi.GetAddresses(id); // Chamada à API para obter endereços
        setAddresses(response.data); // Atualiza o estado com os endereços recebidos
      } catch (err) {
        setError("Erro ao buscar os endereços do cliente.");
        console.error("Erro ao buscar os endereços do cliente:", err);
      }
    };

    fetchClientData();
    fetchAddresses();
  }, [companyId, id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setClientData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setButtonText("Salvando...");

      await AuthApi.UpdatePeople(id, clientData); // Envia os dados atualizados
      history.push("/admin/client"); // Redireciona após salvar
    } catch (err) {
      setButtonText("Salvar Alterações");
      setError("Erro ao atualizar o cliente.");
      console.error("Erro ao atualizar o cliente:", err);
    }
  };

  const handleClose = () => {
    history.push("/admin/client");
  };

  const handleAddressClick = () => {
    history.push(`/admin/${id}/address`); // Redireciona para a página de endereço
  };

  const handleDeleteAddress = async (addressId) => {
    try {
      await AuthApi.DeleteAddress(addressId); // Chamada para deletar o endereço
      setAddresses(addresses.filter((address) => address.id !== addressId)); // Atualiza a lista de endereços
    } catch (err) {
      setError("Erro ao excluir o endereço.");
      console.error("Erro ao excluir o endereço:", err);
    }
  };

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <Flex
        direction={{ base: "column", lg: "row" }} // Coluna em telas pequenas, linha em telas grandes
        maxW="100%"
        w="100%"
        mx="0"
        h="100%"
        alignItems="flex-start" // Alinha ao topo
        justifyContent="flex-start" // Alinha à esquerda
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "15px", md: "5vh" }}
      >
        {/* Seção do Formulário de Edição */}
        <Box
          w={{ base: "100%", lg: "60%" }}
          bg={bgColor}
          p="24px"
          borderRadius="15px"
          boxShadow="lg"
          mr={{ lg: "20px" }} // Espaço à direita da grid em telas grandes
        >
          <Flex
            direction="column"
            w="100%"
            background="transparent"
            borderRadius="15px"
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
                Nome
              </FormLabel>
              <Input
                isRequired
                variant="auth"
                fontSize="sm"
                placeholder="Digite o nome"
                mb="24px"
                name="name"
                value={clientData.name}
                onChange={handleInputChange}
              />
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Tipo
              </FormLabel>
              <Select
                isRequired
                variant="auth"
                fontSize="sm"
                mb="24px"
                name="type"
                value={clientData.type}
                onChange={handleInputChange}
              >
                <option value="C">Cliente</option>
                <option value="P">Loja Parceira</option>
              </Select>
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                {clientData.type === "C" ? "CPF ou CNPJ" : "CNPJ"}
              </FormLabel>
              <Input
                isRequired
                variant="auth"
                fontSize="sm"
                placeholder={clientData.type === "C" ? "Digite o CPF ou CNPJ" : "Digite o CNPJ"}
                mb="24px"
                name="cpf_cnpj"
                value={clientData.cpf_cnpj}
                onChange={handleInputChange}
              />
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Telefone
              </FormLabel>
              <Input
                isRequired
                variant="auth"
                fontSize="sm"
                placeholder="Digite o telefone"
                mb="24px"
                name="fone"
                value={clientData.fone}
                onChange={handleInputChange}
              />
              <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                Email
              </FormLabel>
              <Input
                isRequired
                variant="auth"
                fontSize="sm"
                placeholder="Digite o email"
                mb="24px"
                name="email"
                value={clientData.email}
                onChange={handleInputChange}
              />
              <Flex direction="column" gap="4" mb="24px">
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    h="50px"
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
                    onClick={handleSubmit}
                  >
                    {buttonText}
                  </Button>
                </Flex>
                <Button
                  fontSize="sm"
                  variant="outline"
                  fontWeight="500"
                  w="100%"
                  h="50px"
                  onClick={handleAddressClick}
                >
                  Endereço
                </Button>
              </Flex>
            </FormControl>
          </Flex>
        </Box>

        {/* Seção da Grid de Endereços */}
        <Box
          w={{ base: "100%", lg: "40%" }} // Largura da grid em telas grandes
          bg={bgColor}
          p="24px"
          borderRadius="15px"
          boxShadow="lg"
          overflow="auto"
        >
          <Text fontSize="lg" fontWeight="bold" mb="12px">
            Endereços
          </Text>
          <Grid
            templateColumns={{ base: "repeat(1, 1fr)", md: "repeat(2, 1fr)" }}
            gap="4"
            autoRows="minmax(100px, auto)"
          >
            {addresses.length > 0 ? (
              addresses.map((address) => (
                <GridItem
                  key={address.id}
                  borderWidth="1px"
                  borderRadius="md"
                  p="4"
                  boxShadow="md"
                  bg="white"
                  overflow="hidden"
                >
                  <Flex direction="column" align="start">
                    <Text fontWeight="bold" mb="1">
                      {address.adress} {address.number}
                    </Text>
                    <Text fontSize="sm" color="gray.600" mb="1">
                      {address.district}
                    </Text>
                    <Text fontSize="sm" color="gray.600" mb="1">
                      {address.city}, {address.uf}
                    </Text>
                    <Text fontSize="sm" color="gray.600" mb="1">
                      {address.zipcode}
                    </Text>
                    <Flex justify="flex-end" mt="auto">
                      <IconButton
                        icon={<DeleteIcon />}
                        aria-label="Deletar Endereço"
                        size="sm"
                        colorScheme="red"
                        onClick={() => handleDeleteAddress(address.id)} // Função para deletar o endereço
                      />
                    </Flex>
                  </Flex>
                </GridItem>
              ))
            ) : (
              <Text textAlign="center" color="gray.600">
                Nenhum endereço encontrado
              </Text>
            )}
          </Grid>
        </Box>
      </Flex>
    </Box>
  );
}
