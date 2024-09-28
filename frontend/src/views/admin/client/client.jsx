import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  Grid,
  GridItem,
  Text,
  useBreakpointValue,
  IconButton,
} from "@chakra-ui/react";
import { AddIcon, DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { useHistory } from "react-router-dom"; // Import para navegação
import AuthApi from "../../../api/auth";

export default function CustomerList() {
  const [customers, setCustomers] = useState([]);
  const history = useHistory(); // Usado para navegação
  const companyId = JSON.parse(localStorage.getItem("userId")); // Recupera o userId do localStorage
  
  useEffect(() => {
    const fetchCustomers = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetPeople(companyId); // Passa o userId como parâmetro
        // Ordena os clientes pelo código (id)
        const sortedCustomers = response.data.sort((a, b) => a.id - b.id);
        setCustomers(sortedCustomers);
      } catch (err) {
        console.error("Erro ao buscar clientes:", err);
      }
    };

    fetchCustomers();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)", // Tela pequena: 1 coluna
    sm: "repeat(2, 1fr)",   // Tela pequena/média: 2 colunas
    md: "repeat(3, 1fr)",   // Tela média: 3 colunas
    lg: "repeat(4, 1fr)",   // Tela grande: 4 colunas
  });

  const handleAddCustomerClick = () => {
    history.push("/admin/registerclient"); // Navega para a tela de cadastro
  };

  const handleDeleteCustomer = async (customerId) => {
    try {
      await AuthApi.DeletePeople(customerId);
      // Filtra a lista de clientes para remover o que foi deletado
      setCustomers((prevCustomers) =>
        prevCustomers.filter((customer) => customer.id !== customerId)
      );
    } catch (err) {
      console.error("Erro ao deletar cliente:", err);
    }
  };

  const handleEditCustomer = (customerId) => {
    history.push(`/admin/editclient/${customerId}`); // Navega para a tela de edição com o ID do cliente
  };

  return (
    <Box p="4" position="relative">
      <Grid
        templateColumns={gridTemplateColumns}
        gap="4"
        autoRows="minmax(100px, auto)"
        mt="15vh" // Aumenta a margem superior para mais espaço
      >
        {customers.map((customer) => (
          <GridItem
            key={customer.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg="white"
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">
                Código: {customer.id} - {customer.name} {/* Concatenando código e nome */}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="1">CPF/CNPJ: {customer.cpf_cnpj}</Text> {/* Nome do campo alterado */}
              <Text fontSize="sm" color="gray.600" mb="1">
                Tipo: {customer.type === "C" ? "Cliente" : "Loja Parceira"} {/* Nome do campo alterado */}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="3">Telefone: {customer.fone}</Text> {/* Nome do campo alterado */}
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Cliente"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditCustomer(customer.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Cliente"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeleteCustomer(customer.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Cliente"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px" // Ajuste conforme necessário
        right="16px"  // Ajuste conforme necessário
        onClick={handleAddCustomerClick}
        zIndex="tooltip" // Garante que o botão fique acima de outros elementos
      />
    </Box>
  );
}
