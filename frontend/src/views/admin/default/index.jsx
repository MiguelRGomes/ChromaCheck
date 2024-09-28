import {
  Box,
  SimpleGrid,
} from "@chakra-ui/react";
// Custom components
import TotalSpent from "views/admin/default/components/TotalSpent";

export default function UserReports() {
  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <SimpleGrid columns={{ base: 1, md: 1, xl: 2 }} gap='100px' mb='100px'>
        <TotalSpent />
      </SimpleGrid>
    </Box>
  );
}
