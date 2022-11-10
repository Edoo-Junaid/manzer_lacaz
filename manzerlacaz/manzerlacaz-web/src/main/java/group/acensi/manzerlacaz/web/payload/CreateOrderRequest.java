package group.acensi.manzerlacaz.web.payload;

public record CreateOrderRequest(Long user_id,Long menu_id,Long payment,String option) {

}
