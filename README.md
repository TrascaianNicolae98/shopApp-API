                                                  Shop

Introducere:

   Aplicatia shop este o aplicatie simpla pentru persoanele care vor sa isi creeze liste de cumparaturi pe care sa le aiba mereu la indemana.

Descriere problema:
   Cred ca o aplicatie simpla de genul poate fi foarte utila pentru orice.
   Aplicatia va permite sa adaugati cele mai frecventate magazine de catre dvs.
   La fiecare magazin puteti adauga produsele pe care doriti sa le cumparati(unul cate unul).
   Aplicatia va permite sa stergeti un produs adaugat gresit sau sa-l stergeti dupa ce 
   l-ati cumparat din magazin.De asemenea va permite sa anulati o lista completa de la un magazin prin a sterge toate produsele.

Descriere API:
  Pe partea de backend am folosit java cu spring, gradle pentru dependencies si postgresql pentru baza de date.
  Pentru aceasta aplicatie s-au folosit API-uri de tip REST:

LoginController:

  POST/loginWithGoogle:
  Acest api este folosit pentru logarea cu google.Acesta trimite un jwt frontendului pe care urmeaza sa-l foloseasca ca authorization pentru fiecare call.

ShopController:- 

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/ProductController.png)

   POST/shops
   Acest api este folosit pentru a adauga un magazin.
   
   POST/shops/{shopId}
   Acest api este folosit pentru a sterge un magazin.
   
   GET/shops/{userId}
   Acest api este folosit pentru a returna toate magazinele.


ProductController:


   Post/products
   Acest api este folosit pentru a adauga un produs.
   
   Post/products/{productId}
   Acest api este folosit pentru a sterge un produs.
   
   POST/products/all/{shopId}
   Acest api este folosit pentru a sterge toate produsele dintr-o lista.
   
   GET/products/{shopId}
   Acest api este folosit pentru a returna toate produsele dintr-o lista.
   
   
   
  

Descriere ui:
Pe partea de frontend am folosit angular cu angular material.Call-urile catre backend sunt de tip http. ex:

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/Calls.png)

Flux de date:
Toate datele pe care userul le adauga vor fi salvate in baza de date (inclusiv datele de logare).Cand userul porneste aplicatia acesta va trebui sa se logeze.
Va fi trimis in home page unde isi poate adauga cate magazine doreste.Cand se apasa click pe un magazin se deschide o pagina noua unde se poate adauga lista de produse.

Servicii cloud:

Aplicatia foloseste login cu google.Aceasta primeste token-ul de la google pe care il trimite pe backend.Token-ul este verificat pe backend, 
iar apoi este trimis un jwt pe frontend pentru a fi utilizat ca authorization.
Pe backend s-a folosit websecurity si jwt filter pentru securitatea aplicatiei.
![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/BackLog.png)

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/FrontLog.png)
Pentru rulare locala:
Pentru a rula local aveti nevoie sa va instalati un ide pentru java, angular si postgresql 12.5.Porturile folosite sunt 5432-data base, 8080-backend, 4200-frontend.

LogIn Page:

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/LogIn.png)

Shops Page:

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/Shops2.png)

Products Page:

![image](https://github.com/CosminaCheciu/shopApp-api/blob/master/images/Products.png)

GitHub:

Backend: https://github.com/CosminaCheciu/shopApp-api.git -master branch

Frontend: https://github.com/CosminaCheciu/shopApp-ui.git -master branch




