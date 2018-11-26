<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>div_Welcome.     Log in to acc</name>
   <tag></tag>
   <elementGuidId>021d56b4-253c-4c46-b779-10b078ae1664</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>(.//*[normalize-space(text()) and normalize-space(.)='Login'])[1]/following::div[1]</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>div</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>container auth-container</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>
            
                    
                        

    Welcome.
 
    Log in to access your content.
                        
 
    Don't have an account? Register here.
                     

                        
                            
    
        
            The username, password, or both are invalid.
        
    
    
        
        Please enter your work email address
        
        
        
    
    
        
        Please enter your password
        
        
    
    
        
            Forgot your password?
        
        
            
            
            
            
        
    
    


    
        You are being redirected to  to log in. You will be returned to your service once you have logged in.
    




    var SubmitFormOnSamlCheck = false;
    var FormIsSubmitable = false;

    $(function () {
        var UsernameField = $('#UserName');
        var Form = $(UsernameField).parents('form:first');
        $('#UserName').focusout(CheckUserNameField);
        $(Form).submit(CheckForm);
    });

    function CheckUserNameField(e) {
        CheckUsernameForSaml(false);
    }

    function CheckUsernameForSaml(SubmitForm) {
        SubmitFormOnSamlCheck = SubmitForm ? true : false;

        var Username = $('#UserName').val();
        var EncryptedToken = $('#EncryptedToken').val();
        var EncryptedTokenName = $('#ncryptedTokenName').val();

        var data = {
            Username: Username,
            EncryptedToken: EncryptedToken,
            EncryptedTokenname: EncryptedTokenName
        }

        $.post('/login/Saml/CheckUsernameForSaml', data, CheckUsernameForSamlResponse, &quot;json&quot;);
    }

    function CheckUsernameForSamlResponse(response) {

        var PasswordField = $('#Password');
        if (response) {
            $(PasswordField).attr('disabled', 'disabled');
            ShowSamlInstructions(response.IdPName, response.SamlRequest);
            FormIsSubmitable = false;
        } else {
            $('#saml-message').hide();
            $(PasswordField).removeAttr('disabled');
            FormIsSubmitable = true;

            if (SubmitFormOnSamlCheck) {
                var Form = $(PasswordField).parents('form:first');
                var Username = $('#UserName').val();
                if (!isEmpty(Username) || !isEmpty(PasswordField.val())) {
                    Form.submit();
                }
            }
        }
    }

    function CheckForm(e) {

        if (FormIsSubmitable) {
            this.submit();
            return;
        }

        e.preventDefault();
        CheckUsernameForSaml(true);
    }

    function ShowSamlInstructions(name, url) {
        $('div.validation-summary-errors').hide();
        $(&quot;#IdPName&quot;).html(name);
        $(&quot;.popup-background&quot;).addClass(&quot;show&quot;);
        setTimeout(function () { window.location = url; }, 3500);
    }

    function isEmpty(str) {
        return (!str || 0 === str.length);
    }



                        


                





            
        </value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>/html[1]/body[@class=&quot;background-white&quot;]/div[@class=&quot;container auth-container&quot;]</value>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='Login'])[1]/following::div[1]</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='Register'])[1]/following::div[1]</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <value>//body/div[2]</value>
   </webElementXpaths>
</WebElementEntity>
