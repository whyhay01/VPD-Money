package me.greenworld.vpdmoney.common

interface Mapper<Entity, Data> {

    fun from(cache: Entity): Data

    fun to(data: Data): Entity

    fun mapModelList(models: List<Entity>): List<Data> {
        return models.mapTo(mutableListOf(), ::from)
    }

    fun mapEntityList(entityList: List<Data>): List<Entity> {
        return entityList.mapTo(mutableListOf(), this::to)
    }
}