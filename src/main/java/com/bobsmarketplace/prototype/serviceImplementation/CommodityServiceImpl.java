package com.bobsmarketplace.prototype.serviceImplementation;

import com.bobsmarketplace.prototype.entity.Commodity;
import com.bobsmarketplace.prototype.repository.CommodityRepository;
import com.bobsmarketplace.prototype.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    //Declaration of variables
    private CommodityRepository commodityRepository;

    /**
     * Instantiating the variables for this class.
     *
     * @param commodityRepository
     */
    @Autowired
    public CommodityServiceImpl(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    /**
     * Persist a new Commodity
     *
     * @param newCommodity
     * @return Commodity that is saved into the database.
     */
    @Override
    public Commodity save(Commodity newCommodity) {
        return commodityRepository.save(newCommodity);
    }

    /**
     * Returns all the commodities that is in the database
     *
     * @return List of commodities
     */
    @Override
    public List<Commodity> getAllCommodity() {
        return commodityRepository.findAll();
    }

    /**
     * Finds commodities of given cnid in the database
     *
     * @return List of commodities of given cnid
     */
    @Override
    public List<Commodity> getAllCommodityByCommodityName(Long cnid) {
        return commodityRepository.findAllCommodityOfCommodityName(cnid);
    }

    /**
     * Finds commodities of given seller's id in the database
     *
     * @return List of commodities of given seller's id
     */
    @Override
    public List<Commodity> getAllCommodityBySeller(Long id) {
        return commodityRepository.findAllCommodityOfSeller(id);
    }

    /**
     * Finds a commodity based on its ID and returns the object if found in the database.
     *
     * @param cid
     * @return A commodity if it exists or else it will return null.
     */
    @Override
    public Commodity getCommodityById(Long cid) {
        return commodityRepository.findById(cid).map(category -> {
            return category;
        }).orElse(null);
    }

    /**
     * Finds a commodity based on its commodityNameId and returns a list of commodity objects if found in the database.
     *
     * @param commodityNameId
     * @return A list of commodities if it exists or else it will return an empty list.
     */
    @Override
    public List<Commodity> getCommodityByCommodityNameId(Long commodityNameId) {
        return commodityRepository.findAllCommodityWithCommodityNameId(commodityNameId);
    }

    /**
     * Finds a commodity based on its Seller's ID and returns a list of commodity objects if found in the database.
     *
     * @param sellerId
     * @return A list of commodities if it exists or else it will return an empty list.
     */
    @Override
    public List<Commodity> getCommodityBySellerId(Long sellerId) {
        return commodityRepository.findAllCommodityWithSellerId(sellerId);
    }

    /**
     * Finds a list of commodites based on the min price in the market and returns a list of commodites if more than 1 are found in the database.
     *
     * @param commodityNameId
     * @return A list of commodities if it exists or else it will return null.
     */
    @Override
    public List<Commodity> getAllCommodityWithMinPrice(Long commodityNameId) {
        return commodityRepository.findAllCommodityWithMinPrice(commodityNameId);
    }

    /**
     * Finds a commodity based on the cid and updates its contents with the data inside newCommodity
     * @param cid
     * @param newCommodity
     * @return The updated commodity or else return null
     */
    @Override
    public Commodity updateCommodity(Long cid, Commodity newCommodity) {
        return commodityRepository.findById(cid).map(commodity -> {
            commodity.setPrice(newCommodity.getPrice());
            commodity.setQuantity(newCommodity.getQuantity());
            commodity.setExpressCharge(newCommodity.getExpressCharge());
            commodity.setStandardCharge(newCommodity.getStandardCharge());
            commodity.setCommodityNameEntity(newCommodity.getCommodityNameEntity());
            commodity.setSellerEntity(newCommodity.getSellerEntity());
            return commodityRepository.save(commodity);
        }).orElse(null);
    }

    /**
     * Remove a commodity with the given cid
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a commodity will also remove all its associated information
     */
    @Override
    public void deleteCommodityById(Long cid) {
        commodityRepository.deleteById(cid);
    }
}
