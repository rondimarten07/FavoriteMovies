package com.rondi.core.utils

import com.rondi.core.data.Resource

object ResourceMapper {
    fun <OldType, NewType> mapResource(
        resource: Resource<OldType>,
        transform: ((OldType) -> NewType)?
    ): Resource<NewType> {
        return when (resource) {
            is Resource.Success -> {
                val newData = resource.data?.let { transform?.invoke(it) }
                if (newData != null) {
                    Resource.Success(newData)
                } else {
                    Resource.Error("Error mapping data")
                }
            }

            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(resource.message ?: "")
        }
    }
}