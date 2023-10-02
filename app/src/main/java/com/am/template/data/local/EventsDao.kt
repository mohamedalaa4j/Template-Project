package com.am.template.data.local

import androidx.room.*
import com.am.template.data.local.entity.CartDataEntity
import com.am.template.data.local.entity.HallEntity
import com.am.template.data.local.entity.OptionsEntity
import com.am.template.data.local.entity.ProductEntity

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartData: CartDataEntity)

    @Query("SELECT EXISTS(SELECT * FROM CartDataEntity WHERE productIdFk = :productId)")
    suspend fun isProductExist(productId: String): Boolean

    @Query("UPDATE CartDataEntity SET productQty = productQty + 1 WHERE productIdFk=:productId")
    suspend fun updateQuantity(productId: String)

    @Query("SELECT * FROM CartDataEntity WHERE productIdFk=:productId")
    suspend fun getCartItemById(productId: String): CartDataEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCartItem(cartData: CartDataEntity)

    @Query("DELETE FROM CartDataEntity WHERE id = :mainId")
    suspend fun removeCartItemById(mainId: Int)

    @Query("SELECT * FROM CartDataEntity")
    suspend fun getCartList(): List<CartDataEntity>

    @Query("DELETE FROM CartDataEntity")
    suspend fun clearCartList()
    /////////////////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT EXISTS(SELECT * FROM ProductEntity WHERE id = :productId)")
    suspend fun isProductExist2(productId: Int): Boolean

    @Query("UPDATE ProductEntity SET quantity =:qty  WHERE id=:productId")
    suspend fun updateQuantity2(productId: Int, qty: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    suspend fun getProductList(): List<ProductEntity>

    @Query("DELETE FROM ProductEntity WHERE id = :productId")
    suspend fun removeProductFromCart(productId: Int)

    @Query("DELETE FROM ProductEntity")
    suspend fun clearProductsList()

    //____________________________________________________________________________________________//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHall(hallEntity: HallEntity)

    @Query("SELECT * FROM HallEntity")
    suspend fun getHallList(): List<HallEntity>

    @Query("DELETE FROM HallEntity WHERE hall_id = :hall_id")
    suspend fun removeHallFromCart(hall_id: Int)

    //-------------------------------------------------------//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(optionsEntity: OptionsEntity)

    @Query("SELECT EXISTS(SELECT * FROM OptionsEntity WHERE id = :optionId)")
    suspend fun isOptionExist(optionId: Int): Boolean

    @Query("UPDATE OptionsEntity SET quantity =:qty  WHERE id=:optionId")
    suspend fun updateOptionQuantity(optionId: Int, qty: Int)

    @Query("SELECT * FROM OptionsEntity WHERE id_hall = :id_hall")
    suspend fun getOptionsList(id_hall: String): List<OptionsEntity>

    @Query("DELETE FROM OptionsEntity WHERE id_hall = :hall_id")
    suspend fun deleteOption(hall_id: Int)
}