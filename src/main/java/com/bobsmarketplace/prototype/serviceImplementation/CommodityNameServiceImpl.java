package com.bobsmarketplace.prototype.serviceImplementation;

import com.bobsmarketplace.prototype.entity.CommodityName;
import com.bobsmarketplace.prototype.repository.CategoryRepository;
import com.bobsmarketplace.prototype.repository.CommodityNameRepository;
import com.bobsmarketplace.prototype.service.CommodityNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommodityNameServiceImpl implements CommodityNameService {
    //Declaration of variables
    private CommodityNameRepository commodityNameRepository;
    private CategoryRepository categoryRepository;
    /**
     * Instantiating the variables for this class.
     *
     * @param commodityNameRepository
     */
    @Autowired
    public CommodityNameServiceImpl(CommodityNameRepository commodityNameRepository, CategoryRepository categoryRepository) {
        this.commodityNameRepository = commodityNameRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Persist a new commodityName
     *
     * @param newCommodityName
     * @return Commodity that is saved into the database.
     */
    @Override
    public CommodityName save(CommodityName newCommodityName) {
        return commodityNameRepository.save(newCommodityName);
    }

    /**
     * Returns all the commodityNames that is in the database
     *
     * @return List of commodityNames
     */
    @Override
    public List<CommodityName> getAllCommodityName() {
        return commodityNameRepository.findAll();
    }

    /**
     * Finds a commodityName based on its ID and returns the object if found in the
     * database.
     *
     * @param cnid
     * @return A commodityName if it exists or else it will return null.
     */
    @Override
    public CommodityName getCommodityNameById(Long cnid) {
        return commodityNameRepository.findById(cnid).orElse(null);
    }

    /**
     * Finds a commodityName based on its name and returns the object if found in the database.
     *
     * @param name
     * @return A commodityName if it exists or else it will return null.
     */
    @Override
    public CommodityName getCommodityNameByName(String name) {
        return commodityNameRepository.findCommodityNameByName(name);
    }

    /**
     * Finds a commodityName based on the name and updates its contents with the
     * data inside newCategory
     * 
     * @param cnid
     * @param newCommodityName
     * @return The updated commodityName or else return null
     */
    @Override
    public CommodityName updateCommodityName(Long cnid, CommodityName newCommodityName) {
        return commodityNameRepository.findById(cnid).map(commodityName -> {
            commodityName.setName(newCommodityName.getName());
            commodityName.setPictureUrl(newCommodityName.getPictureUrl());
            commodityName.setUom(newCommodityName.getUom());
            commodityName.setCategoryEntity(categoryRepository.getById(newCommodityName.getCategoryEntity().getCatid()));
            return commodityNameRepository.save(commodityName);
        }).orElse(null);
    }

    @Override
    public void deleteCommodityNameById(Long cnid) {
        commodityNameRepository.deleteById(cnid);
    }
}
