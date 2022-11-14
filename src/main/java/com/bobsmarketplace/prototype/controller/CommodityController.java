package com.bobsmarketplace.prototype.controller;

import com.bobsmarketplace.prototype.entity.Commodity;
import com.bobsmarketplace.prototype.entity.CommodityName;
import com.bobsmarketplace.prototype.entity.Seller;
import com.bobsmarketplace.prototype.exception.commodity.CommodityNotFoundException;
import com.bobsmarketplace.prototype.exception.commodityname.CommodityNameNotFoundException;
import com.bobsmarketplace.prototype.exception.seller.SellerNotFoundException;
import com.bobsmarketplace.prototype.serviceImplementation.CommodityNameServiceImpl;
import com.bobsmarketplace.prototype.serviceImplementation.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/commodity")
public class CommodityController {
    // Declaration of variables
    private CommodityServiceImpl commodityServiceImpl;
    private CommodityNameServiceImpl commodityNameServiceImpl;
    private SellerController sellerController;

    /**
     * Instantiating the variables for this class.
     *
     * @param commodityServiceImpl
     */
    @Autowired
    public CommodityController(CommodityServiceImpl commodityServiceImpl,
            CommodityNameServiceImpl commodityNameServiceImpl, SellerController sellerController) {
        this.commodityServiceImpl = commodityServiceImpl;
        this.commodityNameServiceImpl = commodityNameServiceImpl;
        this.sellerController = sellerController;
    }

    /**
     * Add a new commodity with POST request to "/seller"
     *
     * @param newCommodity
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/seller")
    public void addCommodity(@Valid @RequestBody Commodity newCommodity) {
        if (commodityNameServiceImpl.getCommodityNameById(newCommodity.getCommodityNameEntity().getCnid()) == null) {
            throw new CommodityNameNotFoundException(newCommodity.getCommodityNameEntity().getCnid());
        }
        if (sellerController.getSellerById(newCommodity.getSellerEntity().getId()) == null) {
            throw new SellerNotFoundException();
        }
        commodityServiceImpl.save(newCommodity);
    }

    /**
     * List all commodities in the system
     *
     * @return list of all commodities
     */
    @GetMapping("/")
    public List<Commodity> getCommodity() {
        return commodityServiceImpl.getAllCommodity();
    }

    /**
     * Search for commodities of given cnid.
     * 
     * @param cnid
     * @return list of commodities of given cnid
     */
    @GetMapping("/admin/{cnid}")
    public List<Commodity> getAllCommodityByCommodityName(@PathVariable Long cnid) throws CommodityNotFoundException {
        return commodityServiceImpl.getAllCommodityByCommodityName(cnid);
    }

    /**
     * Search for commodities of given seller's id.
     * 
     * @param id
     * @return list of commodities of given seller's id
     */
    @GetMapping("/seller/{id}")
    public List<Commodity> getAllCommodityBySeller(@PathVariable Long id) throws CommodityNotFoundException {
        return commodityServiceImpl.getAllCommodityBySeller(id);
    }

    /**
     * Search for commodity with the given cid
     * If there is no commodity with the given "cid", throw a
     * CommodityNotFoundException
     *
     * @param cid
     * @return commodity with the given cid
     */
    @GetMapping("/find-by-cid/{cid}")
    public Commodity getCommodityById(@PathVariable Long cid) throws CommodityNotFoundException {
        Commodity commodity = commodityServiceImpl.getCommodityById(cid);
        if (commodity == null) {
            throw new CommodityNotFoundException(cid);
        }
        return commodity;
    }

    /**
     * Search for all commodities in the system that match the commodityId and the
     * market min price
     * 
     * @param commodityNameId
     * @return list of all commodities with the market min price
     */
    @GetMapping("/min-price/{commodityNameId}")
    public List<Commodity> getCommodityWithMinPrice(@PathVariable Long commodityNameId) {
        return commodityServiceImpl.getAllCommodityWithMinPrice(commodityNameId);
    }

    /**
     * If there is no commodity with the given "cid", throw a
     * CommodityNotFoundException
     *
     * @param cid          a Long value
     * @param newCommodity a Commodity object containing the new commodity info to
     *                     be updated
     * @return the updated, or newly added commodity
     */
    @PutMapping("/seller/{cid}")
    public Commodity updateCommodity(@Valid @PathVariable Long cid, @RequestBody Commodity newCommodity)
            throws CommodityNotFoundException {
        if (commodityNameServiceImpl.getCommodityNameById(newCommodity.getCommodityNameEntity().getCnid()) == null) {
            throw new CommodityNameNotFoundException(newCommodity.getCommodityNameEntity().getCnid());
        }
        if (sellerController.getSellerById(newCommodity.getSellerEntity().getId()) == null) {
            throw new SellerNotFoundException();
        }
        Commodity commodity = commodityServiceImpl.updateCommodity(cid, newCommodity);
        if (commodity == null) {
            throw new CommodityNotFoundException(cid);
        }
        return commodity;
    }

    /**
     * Remove a commodity with the DELETE request to "/seller/{cid}"
     * If there is no commodity with the given "cid", throw a
     * CommodityNotFoundException
     *
     * @param cid
     */
    @DeleteMapping("/seller/{cid}")
    public void deleteCommodityById(@PathVariable Long cid) throws CommodityNotFoundException {
        try {
            commodityServiceImpl.deleteCommodityById(cid);
        } catch (EmptyResultDataAccessException e) {
            throw new CommodityNotFoundException(cid);
        }
    }

    /**************************************
     *************** UNUSED ***************
     **************************************/
    /**
     * Search for commodity with the given commodityName
     * If there is no commodity with the given "name", throw a
     * CommodityNameNotFoundException
     *
     * @param name
     * @return commodity with the given cid
     */
    @GetMapping("/find-by-name/{name}")
    public List<Commodity> getCommodityByName(@PathVariable String name) throws CommodityNameNotFoundException {
        CommodityName commodityName = commodityNameServiceImpl.getCommodityNameByName(name);
        if (commodityName == null) {
            throw new CommodityNameNotFoundException(name);
        }
        // List<Commodity> commodity =
        // commodityServiceImpl.getCommodityByCommodityNameId(commodityName.getCnid());
        //
        // if (commodity == null || commodity.isEmpty()) {
        // throw new CommodityNotFoundException(name);
        // }
        return commodityServiceImpl.getCommodityByCommodityNameId(commodityName.getCnid());
    }

    /**
     * Search for all commodities in the system that match the sellerId
     * 
     * @param sellerId
     * @return list of all commodities with the sellerId
     */
    @GetMapping("/seller-commodity/{sellerId}")
    public List<Commodity> getCommodityBySellerId(@PathVariable Long sellerId) throws SellerNotFoundException {
        Seller seller = sellerController.getSellerById(sellerId);
        if (seller == null) {
            throw new SellerNotFoundException();
        }

        // List<Commodity> commodity =
        // commodityServiceImpl.getCommodityBySellerId(sellerId);
        //
        // if (commodity == null || commodity.isEmpty()) {
        // throw new CommodityNotFoundException(sellerId);
        // }

        return commodityServiceImpl.getCommodityBySellerId(sellerId);
    }
}
