<suite name="E-Commerce Suite">
    <listeners>
        <listener class-name="org.testng.reporters.XMLReporter" />
    </listeners>
    <test name="Functional Tests">
        <classes>
            <class name="testcases.LoginTest"/>
            <class name="testcases.AddToCartTest"/>
            <class name="testcases.CheckoutTest"/>
            <class name="testcases.LogoutTest"/>
        </classes>
    </test>
</suite>
