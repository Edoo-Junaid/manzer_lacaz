package group.acensi.dafi.web.payload;

public record CreateMenuRequest(Long id,
        String description,
        String price,
        String day,
        String option) {
}
