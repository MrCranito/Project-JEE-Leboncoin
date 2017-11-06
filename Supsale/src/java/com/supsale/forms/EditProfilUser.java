package com.supsale.forms;

import com.supsale.dao.UserDao;
import com.supsale.entities.User;
import static com.supsale.forms.BaseRegister.EMAIL;
import static com.supsale.forms.BaseRegister.getValeurChamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditProfilUser extends ConnectionUser{

    public EditProfilUser(UserDao userDao) {
        super(userDao);
    }

    public User EditProfil(HttpServletRequest request){
        
        User user = new User();
        HttpSession session = request.getSession();
        
        String email = getValeurChamp( request, EMAIL );
        String password = getValeurChamp( request, PASSWORD );
        String lastName = getValeurChamp(request, LASTNAME);
        String firstName = getValeurChamp(request, FIRSTNAME);
        String phone = getValeurChamp(request, PHONE);
        String postal = getValeurChamp(request, POSTAL);
        
        Long id = (Long)session.getAttribute(ID);
        
        user = userDao.find(id);
         
        if(email != null){
            
            user.setEmail(email);
            session.setAttribute(EMAIL, email);
        }
        
        return user;   
    }
}
