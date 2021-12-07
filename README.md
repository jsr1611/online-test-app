# Online Test Application

Online Test

Bunda :
1)	Subject ( Fan Onatili , Matematika va hkz ) , 
Test , 
Answer , 
User , 
PayType , 
FillBalanceHistory , 
UserTestSolveHistory classlarini OOP conception laridan foydalangan holatda (Encapsulation va hkz) shakllantiring. Dastur bitta admin user bor bolgan holatda ishga tushsin.

2) Dastur ishga tushganda foyudalanuvchida login bo’lish yoki registratsiyadan otish imkoniyati bo’lsin. Agar login bolgan foydalanuvchi admin bolsa unga quyidagi amallarni bajarish imkoni bo’lsin : 
      a) Subject CRUD qilish. 
      b) Test CRUD qilish. Bunda testni javoblarini ham CRUD qila olsin
      c) PayType CRUD qilish. (Abiturient balance ini to’ldirayotganda tanlashi uchun CASH  , PayMe , CLICK va hkz)
      d) FillBalance lar tarixini ko’rish barcha PayType lar kesimida.

3) Agar login bolgan yoki registratsiya qilgan foydalanuvchi Abiturient bo’lsa unga quyidagi amallarni bajarish imkoni bo’lsin : (U ham login bo’lganda username va password larni kiritish orqali tizimga kirsin.) 
  
   - Subject tanlash. Bunda tanlashdan oldin barcha fanlar nomi , testlar soni ,  max ball ,har bir to’g’ri javob uchun beriladigan ball va  fandan test ishlash uchun to’lov summasi haqidagi malumotlar ko’rsatilsin. Subject tanlashi bilanoq balanceda mablag yetarli bo’lsa shu mablagni yechib olsin va shu fandan testlarni ishlash boshlansin. Oxirgi testni ishlashi bilan natijaga subject nomi , max ball , abiturient to’plagan ball ko’rsatilsin.

   - O’zi tomonidan ishlangan testlari tarixini Subjectlar kesimida ko’ra olsin. Bunda subject nomi , max ball , abiturient to’plagan ball ko’rsatilsin.

   - Balance ga pul qoshish. Bunda Abiturientga to’lov turini tanlash imkoni berilsin.


## PROGRESS CHECK

- [x] 1) Class creation

- [ ] 2) Services:

    - [x] Registration service
    
    - [x] Continuous service (while loop, sign in, user verification)
    
    - [x] Subject service
    
    - [x] Test service
  
    - [x] Taking tests
    
    - [ ] Payment service
    
    - [ ] Others
  
- [ ] 3) Database integration
    - [x] Local (txt file)
    - [ ] SQL
    
