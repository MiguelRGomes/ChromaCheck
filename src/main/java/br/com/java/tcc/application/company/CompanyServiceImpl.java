package br.com.java.tcc.application.company;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.company.resources.CompanyRequest;
import br.com.java.tcc.application.company.resources.CompanyResponse;
import br.com.java.tcc.application.company.util.CompanyMapper;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import br.com.java.tcc.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    MessageConfiguration messageConfiguration;

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
        if(!Util.validationDocument(companyDTO.getCnpj())){
            throw new RuntimeException("CPF/CNPj inválido");
        }

        CompanyEntity companyEntity = companyMapper.toCompany(companyDTO);

        return companyMapper.toCompanyDTO(companyRepository.save(companyEntity));
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyDTO) {

        if (!companyRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Empresa)"), HttpStatus.NOT_FOUND);
        }

        CompanyEntity companyEntity = returnCompany(id);
        companyMapper.updateCompanyData(companyEntity, companyDTO);
        CompanyEntity updateEntity = companyRepository.save(companyEntity);
        return companyMapper.toCompanyDTO(updateEntity);
    }

    @Override
    public String delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Empresa)"), HttpStatus.NOT_FOUND);
        }

        companyRepository.deleteById(id);
        return "Empresa id: " + id + " deletada";
    }

    @Override
    public CompanyEntity returnCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Empresa)"), HttpStatus.NOT_FOUND));
    }
}