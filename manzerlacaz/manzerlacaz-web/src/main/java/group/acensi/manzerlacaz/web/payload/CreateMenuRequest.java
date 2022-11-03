package group.acensi.manzerlacaz.web.payload;

public record CreateMenuRequest(Long id,
        String description,
        String price,
        String day,
        String option,
        int weekNum) {
}
