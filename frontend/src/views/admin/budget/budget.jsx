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
import { DeleteIcon, EditIcon, AddIcon } from "@chakra-ui/icons";
import AuthApi from "../../../api/auth";
import { useHistory } from "react-router-dom";

export default function BudgetList() {
  const [budgets, setBudgets] = useState([]);
  const [error, setError] = useState(undefined);

  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  useEffect(() => {
    const fetchBudgets = async () => {
      try {
        const response = await AuthApi.GetBudgets(companyId);
        
        // Ordena os orçamentos pelo ID em ordem crescente
        const sortedBudgets = response.data.sort((a, b) => Number(a.id) - Number(b.id));
        
        setBudgets(sortedBudgets);
      } catch (err) {
        setError("Erro ao buscar os orçamentos.");
        console.error("Erro ao buscar os orçamentos:", err);
      }
    };

    fetchBudgets();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)",
    sm: "repeat(2, 1fr)",
    md: "repeat(3, 1fr)",
    lg: "repeat(4, 1fr)",
  });

  const handleEditBudget = (budgetId) => {
    history.push(`/admin/editbudget/${budgetId}`);
  };

  const handleDeleteBudget = async (budgetId) => {
    try {
      await AuthApi.DeleteBudget(budgetId);
      setBudgets(budgets.filter((budget) => budget.id !== budgetId));
    } catch (err) {
      setError("Erro ao excluir o orçamento.");
      console.error("Erro ao excluir o orçamento:", err);
    }
  };

  const handleAddBudgetClick = () => {
    history.push("/admin/registerbudget");
  };

  return (
    <Box p="4" position="relative">
      <Text mb="36px" color="gray.400" fontWeight="400" fontSize="md">
        Lista de Orçamentos
      </Text>
      {error && (
        <Text color="red.500" textAlign="center">
          {error}
        </Text>
      )}
      <Grid
        templateColumns={gridTemplateColumns}
        gap={4}
        autoRows="minmax(100px, auto)"
        mt="15vh"
      >
        {budgets.map((budget) => (
          <GridItem
            key={budget.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg={budget.approval ? 'green.100' : 'yellow.100'}
            borderColor={budget.approval ? 'green.500' : 'yellow.500'}
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">{`Orçamento: ${budget.id}`}</Text>
              <Text fontWeight="bold" mb="1">{`Cliente: ${budget.personEntity.id} - ${budget.personEntity.name}`}</Text>
              <Text fontSize="sm" color="gray.600" mb="1">{`Endereço: ${budget.adressEntity.adress}, ${budget.adressEntity.number}`}</Text>
              <Text fontSize="sm" color="gray.600" mb="3">
                {`Data de Criação: ${new Date(budget.creation_date).toLocaleDateString()}`}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="3">
                {`Data de Expiração: ${new Date(budget.expiration_date).toLocaleDateString()}`}
              </Text>
              <Text fontSize="sm" color="gray.600" mb="3">{`Total: R$ ${budget.total.toFixed(2)}`}</Text>
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Orçamento"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditBudget(budget.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Orçamento"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeleteBudget(budget.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      {/* Legenda */}
      <Flex mt="4" justifyContent="center">
        <Flex align="center" mr="8">
          <Box w="16px" h="16px" bg="green.100" border="2px solid" borderColor="green.500" mr="2" />
          <Text fontSize="sm">Aprovado</Text>
        </Flex>
        <Flex align="center" mr="8">
          <Box w="16px" h="16px" bg="yellow.100" border="2px solid" borderColor="yellow.500" mr="2" />
          <Text fontSize="sm">Pendente</Text>
        </Flex>
        <Flex align="center">
          <Box w="16px" h="16px" bg="red.100" border="2px solid" borderColor="red.500" mr="2" />
          <Text fontSize="sm">Cancelado</Text>
        </Flex>
      </Flex>

      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Orçamento"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px"
        right="16px"
        onClick={handleAddBudgetClick}
        zIndex="tooltip"
      />
    </Box>
  );
}
