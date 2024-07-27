package br.com.java.tcc.application.company;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.company.resources.CompanyRequest;
import br.com.java.tcc.application.company.resources.CompanyResponse;
import br.com.java.tcc.application.company.util.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse findById(Long id) {
        return companyMapper.toCompanyDTO(returnCompany(id));
    }

    @Override
    public List<CompanyResponse> findAll() {

        return companyMapper.toCompanyDTO(companyRepository.findAll());
    }

    @Override
    public CompanyResponse register(CompanyRequest companyDTO) {

        CompanyEntity companyEntity = companyMapper.toCompany(companyDTO);

        return companyMapper.toCompanyDTO(companyRepository.save(companyEntity));
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyDTO) {

        CompanyEntity companyEntity = returnCompany(id);
        companyMapper.updateCompanyData(companyEntity, companyDTO);
        return companyMapper.toCompanyDTO(companyRepository.save(companyEntity));
    }

    @Override
    public String delete(Long id) {
        companyRepository.deleteById(id);
        return "Company id: " + id + " deleted";
    }

    @Override
    public CompanyEntity returnCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Company wasn't found on database"));
    }
}