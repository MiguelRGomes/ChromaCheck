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

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));
  
  useEffect(() => {
    const fetchProducts = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetProduct(companyId); // Assumindo que a API retorna os produtos
        const sortedProducts = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setProducts(sortedProducts);
      } catch (err) {
        console.error("Erro ao buscar produtos:", err);
      }
    };

    fetchProducts();
  }, [companyId]);

  const gridTemplateColumns = useBreakpointValue({
    base: "repeat(1, 1fr)",
    sm: "repeat(2, 1fr)",
    md: "repeat(3, 1fr)",
    lg: "repeat(4, 1fr)",
  });

  const handleAddProductClick = () => {
    history.push("/admin/registerproduct"); // Navega para a tela de cadastro de produto
  };

  const handleDeleteProduct = async (productId) => {
    try {
      await AuthApi.DeleteProduct(productId); // Assumindo que este método deleta produtos
      setProducts((prevProducts) =>
        prevProducts.filter((product) => product.id !== productId)
      );
    } catch (err) {
      console.error("Erro ao deletar produto:", err);
    }
  };

  const handleEditProduct = (productId) => {
    history.push(`/admin/editproduct/${productId}`); // Navega para a tela de edição do produto
  };

  return (
    <Box p="4" position="relative">
      <Grid
        templateColumns={gridTemplateColumns}
        gap="4"
        autoRows="minmax(100px, auto)"
        mt="15vh"
      >
        {products.map((product) => (
          <GridItem
            key={product.id}
            borderWidth="1px"
            borderRadius="md"
            p="4"
            boxShadow="md"
            bg="white"
            overflow="hidden"
          >
            <Flex direction="column" align="start">
              <Text fontWeight="bold" mb="1">Código: {product.id} - {product.name}</Text> {/* Exibindo o nome do produto */}
              <Text color="gray.500" mb="3">Descrição: {product.description}</Text> {/* Exibindo a descrição do produto */}
              <Flex justify="flex-end" mt="auto">
                <IconButton
                  icon={<EditIcon />}
                  aria-label="Editar Produto"
                  size="sm"
                  colorScheme="blue"
                  mr="2"
                  onClick={() => handleEditProduct(product.id)}
                />
                <IconButton
                  icon={<DeleteIcon />}
                  aria-label="Deletar Produto"
                  size="sm"
                  colorScheme="red"
                  onClick={() => handleDeleteProduct(product.id)}
                />
              </Flex>
            </Flex>
          </GridItem>
        ))}
      </Grid>
      <IconButton
        icon={<AddIcon />}
        aria-label="Adicionar Novo Produto"
        size="lg"
        colorScheme="blue"
        position="fixed"
        bottom="16px"
        right="16px"
        onClick={handleAddProductClick}
        zIndex="tooltip"
      />
    </Box>
  );
}
