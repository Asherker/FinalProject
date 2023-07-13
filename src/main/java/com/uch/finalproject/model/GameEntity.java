package com.uch.finalproject.model;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class GameEntity {
    @Schema(description = "遊戲ID")
    int id;

    @Schema(description = "遊戲名稱")
    String name;

    @Schema(description = "遊戲分類")
    String category;

    @Schema(description = "價格")
    int price;

    @Schema(description = "最新進貨日")
    Date inchange;

    @Schema(description = "最後進貨日")
    Date outchange;

    @Schema(description = "數量")
    int quantity;

    @Schema(description = "開發商")
    String developer;

    @Schema(description = "平台")
    String platform;
}