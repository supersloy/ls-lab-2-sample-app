terraform {
    required_providers {
        vkcs = {
            source = "vk-cs/vkcs"
        }
    }
}    
provider "vkcs" {
    username = "r.muravev@innopolis.university"
    password = "Parolotboga123"
    project_id = "786659c7329b4f52adcf05bce29be0bf"
    region = "RegionOne"
}
