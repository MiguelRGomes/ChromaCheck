import React, { useState, useEffect } from "react";
import {
  Box,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Button,
  Text,
  useColorModeValue,
  IconButton,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalCloseButton,
  ModalBody,
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";
import { useHistory, useParams } from "react-router-dom";
import AuthApi from "../../../api/auth";

export default function BudgetProductForm() {
  const { id } = useParams(); // Obtendo o código do orçamento da URL
  const [productCode, setProductCode] = useState("");
  const [productName, setProductName] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [price, setPrice] = useState(0);
  const [total, setTotal] = useState(0);
  const [products, setProducts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const bgColor = useColorModeValue("white", "gray.700");

  useEffect(() => {
    const fetchProducts = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetProduct(companyId);
        const sortedProducts = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setProducts(sortedProducts);
      } catch (err) {
        console.error("Erro ao buscar produtos:", err);
      }
    };

    fetchProducts();
  }, [companyId]);

  const handleProductSelect = (selectedProduct) => {
    setProductCode(selectedProduct.id);
    setProductName(selectedProduct.name);
    setPrice(0); // Preço é definido pelo usuário
    setQuantity(1); // Reseta a quantidade ao selecionar um novo produto
    setTotal(0); // Reseta o total ao selecionar um novo produto
    setIsModalOpen(false);
  };

  const handleQuantityChange = (e) => {
    const newQuantity = e.target.value;
    setQuantity(newQuantity);
    setTotal(price * newQuantity);
  };

  const handlePriceChange = (e) => {
    const newPrice = parseFloat(e.target.value) || 0; // Garante que o valor seja numérico
    setPrice(newPrice);
    setTotal(newPrice * quantity); // Atualiza o total com base na nova quantidade
  };

  const openProductList = () => {
    setIsModalOpen(true);
  };

  const handleSubmit = async () => {
    try {
      const payload = {
        budgetEntity: { id: id },
        productEntity: { id: productCode },
        quantity: parseFloat(quantity),
        unit_price: parseFloat(price),
        total: parseFloat(total),
        approval: false,
      };
      console.log("Salvar produto no orçamento:", payload);
      // Aqui você pode enviar o payload para a API
      await AuthApi.RegisterProductBudget(payload); // Substitua por sua chamada real
      // Redirecione ou mostre mensagem de sucesso conforme necessário
      history.push(`/admin/editbudget/${id}`);
    } catch (error) {
      console.error("Erro ao salvar produto:", error);
    }
  };

  const handleClose = () => {
    history.push(`/admin/editbudget/${id}`);
  };

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <Flex
        maxW={{ base: "100%", md: "max-content" }}
        w="100%"
        mx={{ base: "auto", lg: "0px" }}
        h="100%"
        alignItems="start"
        justifyContent="center"
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "15px", md: "5vh" }}
        flexDirection="column"
      >
        <Box>
          <Text
            mb="36px"
            ms="4px"
            color={textColorSecondary}
            fontWeight="400"
            fontSize="md"
          >
            Adicione produtos ao orçamento abaixo
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
              <FormControl>
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Código do Orçamento
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={id}
                  isReadOnly // Campo somente leitura
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Código do Produto
                </FormLabel>
                <Flex mb="24px">
                  <Input
                    variant="auth"
                    fontSize="sm"
                    value={productCode}
                    isReadOnly
                  />
                  <IconButton
                    icon={<AddIcon />}
                    aria-label="Selecionar Produto"
                    onClick={openProductList}
                    ml={2}
                  />
                </Flex>
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Nome do Produto
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={productName}
                  isReadOnly
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Quantidade
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  type="number"
                  value={quantity}
                  onChange={handleQuantityChange}
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Preço
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  type="number"
                  value={price}
                  onChange={handlePriceChange}
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Total
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={total}
                  isReadOnly
                  mb="24px"
                />
                <Flex justifyContent="space-between">
                  <Button
                    fontSize="sm"
                    variant="outline"
                    fontWeight="500"
                    w="48%"
                    h="50"
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
                    onClick={handleSubmit}
                  >
                    Salvar
                  </Button>
                </Flex>
              </FormControl>
            </Flex>
          </Box>
        </Box>
      </Flex>

      {/* Modal para selecionar produtos */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} size="lg">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Selecione um Produto</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            {products.length > 0 ? (
              products.map((product) => (
                <Button
                  key={product.id}
                  onClick={() => handleProductSelect(product)}
                  width="100%"
                  mb={2}
                >
                  {product.name}
                </Button>
              ))
            ) : (
              <Text>Nenhum produto encontrado.</Text>
            )}
          </ModalBody>
        </ModalContent>
      </Modal>
    </Box>
  );
}
