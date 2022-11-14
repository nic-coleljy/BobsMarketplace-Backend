package com.bobsmarketplace.prototype.controller;

import com.bobsmarketplace.prototype.entity.Commodity;
import com.bobsmarketplace.prototype.entity.CommodityName;
import com.bobsmarketplace.prototype.entity.Transaction;
import com.bobsmarketplace.prototype.entity.dto.commodityName.CommodityNameResponse;
import com.bobsmarketplace.prototype.entity.dto.commodityName.DateTimePrice;
import com.bobsmarketplace.prototype.entity.dto.commodityName.SearchRequest;
import com.bobsmarketplace.prototype.entity.dto.commodityName.SortByDateTimeAscending;
import com.bobsmarketplace.prototype.entity.dto.commodityName.SortByPriceAscending;
import com.bobsmarketplace.prototype.entity.dto.commodityName.SortByPriceDescending;
import com.bobsmarketplace.prototype.exception.commodityname.CommodityNameNotFoundException;
import com.bobsmarketplace.prototype.serviceImplementation.CommodityNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/commodityname")
public class CommodityNameController {
    // Declaration of variables
    private CommodityNameServiceImpl commodityNameServiceImpl;
    private CommodityController commodityController;

    /**
     * Instantiating the variables for this class.
     *
     * @param commodityNameServiceImpl
     */
    @Autowired
    public CommodityNameController(CommodityNameServiceImpl commodityNameServiceImpl,
            CommodityController commodityController) {
        this.commodityNameServiceImpl = commodityNameServiceImpl;
        this.commodityController = commodityController;
    }

    /**
     * Add a new commodityName with POST request to "/admin"
     *
     * @param newCommodityName
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin")
    public void addCommodityName(@Valid @RequestBody CommodityName newCommodityName) {
        commodityNameServiceImpl.save(newCommodityName);
    }

    /**
     * List all sellable commodityNames with price in the system
     *
     * @return list of all sellable commodityNames with price
     */
    @GetMapping("/get-all-sellable-commodity")
    public List<CommodityNameResponse> getAllSellableCommodityName() {
        List<CommodityNameResponse> responses = new LinkedList<>();
        List<CommodityName> commodityNames = commodityNameServiceImpl.getAllCommodityName();
        for (CommodityName commodityName : commodityNames) {
            CommodityNameResponse response = getResponse(commodityName);
            if (response.getCommodity() != null) {
                responses.add(response);
            }
        }
        return responses;
    }

    /**
     * List searched sellable commodityNames in the system
     *
     * @return list of searched sellable commodityNames in requested order
     */
    @PostMapping("/search")
    public List<CommodityNameResponse> getSearchedSellableCommodityName(@RequestBody SearchRequest searchRequest) {
        String searchTerm = searchRequest.getSearchTerm().toLowerCase();
        List<CommodityNameResponse> responses = getAllSellableCommodityName();
        responses.removeIf(r -> !(r.getName().toLowerCase().contains(searchTerm)
                || r.getCategoryEntity().getName().toLowerCase().contains(searchTerm)));
        List<String> categories = searchRequest.getCategories();
        if (!categories.isEmpty()) {
            responses.removeIf(r -> !categories.contains(r.getCategoryEntity().getName()));
        }
        switch (searchRequest.getSort()) {
            case "Price - Descending":
                Collections.sort(responses, new SortByPriceDescending());
                break;
            case "Price - Ascending":
                Collections.sort(responses, new SortByPriceAscending());
                break;
            default:
                break;
        }
        return responses;
    }

    /**
     * List all commodityNames with price in the system
     *
     * @return list of all commodityNames with price
     */
    @GetMapping("/admin")
    public List<CommodityNameResponse> getAllCommodityName() {
        List<CommodityNameResponse> responses = new LinkedList<>();
        List<CommodityName> commodityNames = commodityNameServiceImpl.getAllCommodityName();
        for (CommodityName commodityName : commodityNames) {
            responses.add(getResponse(commodityName));
        }
        return responses;
    }

    /**
     * Search for commodityName with the given cnid
     * If there is no commodityName with the given "cnid", throw a
     * CommodityNameNotFoundException
     *
     * @param cnid
     * @return commodityName with the given cnid
     */
    @GetMapping("/user/{cnid}")
    public CommodityNameResponse getCommodityNameById(@PathVariable Long cnid) throws CommodityNameNotFoundException {
        CommodityName commodityName = commodityNameServiceImpl.getCommodityNameById(cnid);
        if (commodityName == null) {
            throw new CommodityNameNotFoundException(cnid);
        }
        return getResponse(commodityName);
    }

    /**
     * Search for commodityName with the given name
     * If there is no commodityName with the given "name", throw a
     * CommodityNameNotFoundException
     *
     * @param name
     * @return commodityName with the given name
     */
    @GetMapping("/find-by-name/{name}")
    public CommodityName getCommodityNameByName(@PathVariable String name) throws CommodityNameNotFoundException {
        CommodityName commodityName = commodityNameServiceImpl.getCommodityNameByName(name);
        if (commodityName == null) {
            throw new CommodityNameNotFoundException(name);
        }
        return commodityNameServiceImpl.getCommodityNameByName(name);
    }

    /**
     * If there is no commodityName with the given "cnid", throw a
     * CommodityNameNotFoundException
     *
     * @param cnid             a Long value
     * @param newCommodityName a CommodityName object containing the new
     *                         commodityName info to be updated
     * @return the updated, or newly added commodityName
     */
    @PutMapping("/admin/{cnid}")
    public CommodityName updateCommodityName(@Valid @PathVariable Long cnid,
            @RequestBody CommodityName newCommodityName) throws CommodityNameNotFoundException {
        CommodityName commodityName = commodityNameServiceImpl.updateCommodityName(cnid, newCommodityName);
        if (commodityName == null) {
            throw new CommodityNameNotFoundException(cnid);
        }
        return commodityName;
    }

    /**
     * Remove a commodityName with the DELETE request to "/admin/{cnid}"
     * If there is no commodityName with the given "cnid", throw a
     * CommodityNameNotFoundException
     *
     * @param cnid
     */
    @DeleteMapping("/admin/{cnid}")
    public void deleteCommodityNameById(@PathVariable Long cnid) throws CommodityNameNotFoundException {
        try {
            commodityNameServiceImpl.deleteCommodityNameById(cnid);
        } catch (EmptyResultDataAccessException e) {
            throw new CommodityNameNotFoundException(cnid);
        }
    }

    /**
     * Convert commodityName to response that include commodity with min price
     *
     * @return Response of commodityName including commodity with min price
     */
    private CommodityNameResponse getResponse(CommodityName commodityName) {
        // Get hashmap of datetime-price
        List<Commodity> commodities = commodityController.getAllCommodityByCommodityName(commodityName.getCnid());
        List<DateTimePrice> pastPrices = new LinkedList<>();
        for(Commodity commodity : commodities) {
            for(Transaction transaction: commodity.getTransactions()) {
                pastPrices.add(new DateTimePrice(transaction.getDateTime(), transaction.getMinPrice()));
            }
        }
        Collections.sort(pastPrices, new SortByDateTimeAscending());
        // Get commodity with min price
        commodities = commodityController.getCommodityWithMinPrice(commodityName.getCnid());
        Commodity commodity = null;
        if (commodities != null && !commodities.isEmpty()) {
            commodity = commodities.get(0);
        }
        return new CommodityNameResponse(
                commodityName.getCnid(), commodityName.getName(),
                commodityName.getPictureUrl(), commodityName.getUom(),
                commodityName.getCategoryEntity(),
                commodity, pastPrices);
    }
}
