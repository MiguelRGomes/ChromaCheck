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
import { useHistory } from "react-router-dom";
import AuthApi from "../../../api/auth";

export default function PriceList() {
  const [prices, setPrices] = useState([]);
  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  useEffect(() => {
    const fetchPrices = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetPrice(companyId); // Assumindo que a API retorna os preços
        const sortedPrices = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setPrices(sortedPrices);
      } catch (err) {
        console.error("Erro ao buscar preços:", err);
      }
    };

    fetchPrices();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)",
    sm: "repeat(2, 1fr)",
    md: "repeat(3, 1fr)",
    lg: "repeat(4, 1fr)",
  });

  const handleAddPriceClick = () => {
    history.push("/admin/registerprice"); // Navega para a tela de cadastro de preço
  };

  const handleDeletePrice = async (priceId) => {
    try {
      await AuthApi.DeletePrice(priceId); // Assumindo que este método deleta preços
      setPrices((prevPrices) =>
        prevPrices.filter((price) => price.id !== priceId)
      );
    } catch (err) {
      console.error("Erro ao deletar preço:", err);
    }
  };

  const handleEditPrice = (priceId) => {
    history.push(`/admin/editprice/${priceId}`); // Navega para a tela de edição do preço
  };

  return (
    <Box p="4" position="relative">
      <Grid
        templateColumns={gridTemplateColumns}
        gap="4"
        autoRows="minmax(100px, auto)"
        mt="15vh"
      >
        {prices.map((price) => (
          <GridItem
            key={price.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg="white"
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">Código: {price.id} - {price.name}</Text> {/* Exibe o código do serviço */}
              <Text color="gray.500" mb="1">Preço por m²: {price.square_meter}</Text> {/* Exibindo o preço por metro quadrado */}
              <Text color="gray.500" mb="3">Preço Fixo: {price.fixed_price}</Text> {/* Exibindo o preço fixo */}
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Preço"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditPrice(price.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Preço"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeletePrice(price.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Preço"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px"
        right="16px"
        onClick={handleAddPriceClick}
        zIndex="tooltip"
      />
    </Box>
  );
}
