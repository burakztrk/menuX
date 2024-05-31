
<img width="906" alt="image" src="https://github.com/burakztrk/menuX/assets/8226733/e1f0f583-798f-4de6-82b5-eb95d928f79e">
<img width="231" alt="image" src="https://github.com/burakztrk/menuX/assets/8226733/fe6c4749-1562-4dcd-a141-e8d255cdc4e9">
<img width="486" alt="image" src="https://github.com/burakztrk/menuX/assets/8226733/2285537c-e5cd-4452-b438-7d14ea9bc704">

- V1
    - [x]  Seçilen yaml testini çalıştır
    - [x]  AppIdyi parametre olarak al
    - [x]  Appidyi alabilcek config panel ekle
    - [x]  Storage ve config
    - [x]  Builder patterna al
    
- V2
    - [ ]  Birden fazla testi run et
    - [ ]  Folderı run et
    - [x]  Context menu de sadece yamllar için menüde göster
- Diğer eklemeler
    - [ ]  Generic command runner.
    
- Generic Command Runner
    
    Config ekranında ekle butonu olur istenildiği kadar komut eklenebilir
    
    Eklenen her komut contexMenude commandrunner altında listelenir
    
    FilterExt Name ve command girilir kaydedilir
    
    Config ekranında input alanı komut girilir ve path için wildcard koyulur örnek 
    
    ```bash
    ext yaml
    name maestro stage run
    maestro test -e APPID=com.trendyol.go.stage %selectedFilePath
    
    ext yaml
    name maestro prod run
    maestro test -e APPID=com.trendyol.go %selectedFilePath
    ```
