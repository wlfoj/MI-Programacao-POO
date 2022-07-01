package tests;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;


@Suite
@SelectClasses({
	ManagementCostumeTests.class,
	ManagementUsersTests.class,
	ManagementProductsTests.class,
	ManagementProviderTests.class,
	ManagementItensTests.class,
	ManagementSalesTests.class,
	})

class MySuiteTests {
}
