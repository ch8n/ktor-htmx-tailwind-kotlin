package com.data.models


data class Customer(
    val id: String,
    val phone: String,
    val email: String,
    val customerAddress: List<CustomerAddress>,
    val customerOrder: List<CustomerOrder>
)

data class CustomerAddress(
    val id: String,
    val customerId: String,
    val address: String,
    val city: String,
    val state: String,
    val pinCode: String,
    val contactPhone: String
)

data class CustomerOrder(
    val id: String,
    val customerId: String,
    val orderIds: List<String>
)

data class Collection(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
)

data class Product(
    val id: String,
    val collectionId: String,
    val title: String,
    val description: String,
    val productVarient: List<ProductVarient>,
    val productInventory: ProductInventory
)

data class ProductInventory(
    val id: String,
    val productId: String,
    val varientId: String,
    val qty: Int,
)

data class ProductVarient(
    val id: String,
    val productId: String,
    val imageUrl: List<String>,
    val priceRupees: Double,
    val comparedPriceRupees: Double,
    val weightInKg: Double,
    val size: Double,
    val material: String,
)

data class Order(
    val id: String,
    val customerId: String,
    val orderItem: List<OrderItem>,
    val comments: List<String>,
    val orderStatus: OrderStatus,
    val discount: Discount? = null
)

enum class OrderStatus {
    Unpaid,
    Paid,
    Fulfilled,
    Unfulfilled,
}

data class OrderItem(
    val id: String,
    val orderId: String,
    val productId: String,
    val qty: Int,
    val price: Double,
)

data class Discount(
    val id: String,
    val orderId: String,
    val discountName: String,
    val discountType: DiscountType,
    val minimumPurchaseRequirement: MinimumPurchaseRequirement,
    val customerEligibility: CustomerEligibility,
    val maxDiscountUsage: MaxDiscountUsage,
    val discountCombination: DiscountCombination,
    val activeDates: ActiveDates,
    val timesUsed: Long,
)

sealed class DiscountType {
    data class Percentage(val value: Double, val upto: Double) : DiscountType()
}

sealed class MinimumPurchaseRequirement {
    data object None : MinimumPurchaseRequirement()
    data class MinPurchaseAmount(val value: Double) : MinimumPurchaseRequirement()
    data class MinQuantity(val value: Int) : MinimumPurchaseRequirement()
}

sealed class CustomerEligibility {
    data object All : CustomerEligibility()
}

sealed class MaxDiscountUsage {
    data object SingleUse : MaxDiscountUsage()
    data class MultipleUse(val value: Int) : MaxDiscountUsage()
}

enum class DiscountCombination {
    ProductDiscount,
    OrderDiscount,
    ShippingDiscount,
}

data class ActiveDates(
    val fromTime: Long,
    val toTime: Long
)
