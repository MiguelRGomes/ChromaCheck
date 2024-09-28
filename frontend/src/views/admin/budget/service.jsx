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

export default function BudgetServiceForm() {
  const { id } = useParams(); // Obtendo o código do orçamento da URL
  const [serviceCode, setServiceCode] = useState("");
  const [serviceName, setServiceName] = useState("");
  const [priceCode, setPriceCode] = useState("");
  const [priceName, setPriceName] = useState(""); // Novo estado para nome do preço
  const [priceValue, setPriceValue] = useState(""); // Novo estado para nome do preço
  const [quantity, setQuantity] = useState(1);
  const [discount, setDiscount] = useState(0);
  const [total, setTotal] = useState(0);
  const [services, setServices] = useState([]);
  const [prices, setPrices] = useState([]); // Novo estado para preços
  const [isServiceModalOpen, setIsServiceModalOpen] = useState(false);
  const [isPriceModalOpen, setIsPriceModalOpen] = useState(false); // Novo estado para o modal de preços
  const history = useHistory();
  const companyId = JSON.parse(localStorage.getItem("userId"));

  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const bgColor = useColorModeValue("white", "gray.700");

  useEffect(() => {
    const fetchServices = async () => {
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetService(companyId);
        const sortedServices = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setServices(sortedServices);
      } catch (err) {
        console.error("Erro ao buscar serviços:", err);
      }
    };

    const fetchPrices = async () => { // Nova função para buscar preços
      if (!companyId) {
        console.error("Usuário não está logado.");
        return;
      }

      try {
        const response = await AuthApi.GetPrice(companyId); // Supondo que você tenha uma função para buscar preços
        const sortedPrices = response.data.sort((a, b) => a.name.localeCompare(b.name));
        setPrices(sortedPrices);
      } catch (err) {
        console.error("Erro ao buscar preços:", err);
      }
    };

    fetchServices();
    fetchPrices(); // Chama a função para buscar preços
  }, [companyId]);

  const handleServiceSelect = (selectedService) => {
    setServiceCode(selectedService.id);
    setServiceName(selectedService.name);
    setPriceCode(selectedService.priceId || ""); // Supondo que o serviço tenha um campo priceId
    setQuantity(1); // Reseta a quantidade ao selecionar um novo serviço
    setDiscount(0); // Reseta o desconto ao selecionar um novo serviço
    setTotal(0); // Reseta o total ao selecionar um novo serviço
    setIsServiceModalOpen(false);
  };

  const handlePriceSelect = (selectedPrice) => { // Nova função para selecionar o preço
    setPriceCode(selectedPrice.id);
    setPriceName(selectedPrice.name); // Armazena o nome do preço
    setPriceValue(selectedPrice.fixed_price);
    setIsPriceModalOpen(false);
  };

  const handleQuantityChange = (e) => {
    const newQuantity = e.target.value;
    setQuantity(newQuantity);
    updateTotal(newQuantity, discount);
  };

  const handleDiscountChange = (e) => {
    const newDiscount = parseFloat(e.target.value) || 0; // Garante que o valor seja numérico
    setDiscount(newDiscount);
    updateTotal(quantity, newDiscount);
  };

  const updateTotal = (quantity, discount) => {
    // Calcula o total com base na quantidade e desconto
    const servicePrice = 100; // Você pode ajustar isso para pegar o preço real do serviço
    const newTotal = (servicePrice * quantity) * ((100 - discount) / 100);
    setTotal(newTotal);
  };

  const openServiceList = () => {
    setIsServiceModalOpen(true);
  };

  const openPriceList = () => { // Função para abrir o modal de preços
    setIsPriceModalOpen(true);
  };

  const handleSubmit = async () => {
    try {
      const payload = {
        budgetEntity: { id: id },              
        servicesEntity: { id: serviceCode }, 
        pricesEntity: { id: priceCode },
        quantity: parseFloat(quantity),
        discount: parseFloat(discount),
      };

      console.log("Salvar serviço no orçamento:", payload);
      // Aqui você pode enviar o payload para a API
      await AuthApi.RegisterServiceBudget(payload); // Substitua por sua chamada real
      // Redirecione ou mostre mensagem de sucesso conforme necessário
      history.push(`/admin/editbudget/${id}`);
    } catch (error) {
      console.error("Erro ao salvar serviço:", error);
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
            Adicione serviços ao orçamento abaixo
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
                  Código do Serviço
                </FormLabel>
                <Flex mb="24px">
                  <Input
                    variant="auth"
                    fontSize="sm"
                    value={serviceCode}
                    isReadOnly
                  />
                  <IconButton
                    icon={<AddIcon />}
                    aria-label="Selecionar Serviço"
                    onClick={openServiceList}
                    ml={2}
                  />
                </Flex>
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Nome do Serviço
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={serviceName}
                  isReadOnly
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Código Tabela de Preço
                </FormLabel>
                <Flex mb="24px">
                  <Input
                    variant="auth"
                    fontSize="sm"
                    value={priceCode}
                    isReadOnly
                  />
                  <IconButton
                    icon={<AddIcon />}
                    aria-label="Selecionar Preço"
                    onClick={openPriceList} // Chama a função para abrir o modal de preços
                    ml={2}
                  />
                </Flex>
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Nome Tabela de Preço
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={priceName} // Novo campo para mostrar o nome do preço
                  isReadOnly
                  mb="24px"
                />
                <FormLabel fontSize="sm" fontWeight="500" color={textColor} mb="8px">
                  Valor
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  value={priceValue} // Novo campo para mostrar o nome do preço
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
                  Desconto (%)
                </FormLabel>
                <Input
                  variant="auth"
                  fontSize="sm"
                  type="number"
                  value={discount}
                  onChange={handleDiscountChange}
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

      {/* Modal de Serviços */}
      <Modal isOpen={isServiceModalOpen} onClose={() => setIsServiceModalOpen(false)}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Selecionar Serviço</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            {services.map((service) => (
              <Button
                key={service.id}
                onClick={() => handleServiceSelect(service)}
                w="100%"
                mb={2}
              >
                {service.name}
              </Button>
            ))}
          </ModalBody>
        </ModalContent>
      </Modal>

      {/* Modal de Preços */}
      <Modal isOpen={isPriceModalOpen} onClose={() => setIsPriceModalOpen(false)}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Selecionar Preço</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            {prices.map((price) => (
              <Button
                key={price.id}
                onClick={() => handlePriceSelect(price)} // Chama a função para selecionar o preço
                w="100%"
                mb={2}
              >
                {price.name} {/* Exibe o nome do preço */}
              </Button>
            ))}
          </ModalBody>
        </ModalContent>
      </Modal>
    </Box>
  );
}
