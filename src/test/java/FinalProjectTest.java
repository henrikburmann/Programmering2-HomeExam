import ntnu.idatt2001.henriabu.finalproject.PostPlace;
import ntnu.idatt2001.henriabu.finalproject.PostPlaceRegister;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;
import org.junit.jupiter.api.*;

public class FinalProjectTest {
    PostPlace p1;
    PostPlace p2;
    PostPlace p3;
    PostPlaceRegister register;

    @BeforeEach
    //Called before each test
    public void beforeEach() throws InvalidPostalCodeException, InvalidTownException {
        register = new PostPlaceRegister();
        p2 = new PostPlace("2045", "Borgen", "3033",
                "Ullensaker", "G");
        p3 = new PostPlace("2045", "Sydney", "1917",
                "Austalia", "A");
    }

    @Nested

    class PostPlaceTest {

        @Test
        @DisplayName("Creating an object of PostalCodes with an invalid postalcode")
        /**
         * PostPlace with invalid postal code is being created. That should result in an InvalidPostalCodeException
         * being thrown
         */
        public void invalidPCShouldThrowInvalidPostalCodeException(){
            Assertions.assertThrows(InvalidPostalCodeException.class, () ->{
                //postalCode contains "A" and is therefore invalid
                p1 = new PostPlace("A123", "Kløfta", "3033",
                        "Ullensaker", "G");
            });
        }

        @Test
        @DisplayName("Creating an object of PostalCodes with invalid town")
        /** PostPlace with invalid town is being created. That should result in an InvalidTownException
         * being thrown
         */
        public void invalidPOShouldThrowInvalidTownException(){
            Assertions.assertThrows(InvalidTownException.class, () ->{
                //town contains "1" and is therefore invalid
                p1 = new PostPlace("2040", "1Kløfta", "3033",
                        "Ullensaker", "G");
            } );
        }

        @Test
        @DisplayName("Creating an object with valid fields")
        /**
         * PostPlace with all valid fields being created. Should not throw any exceptions
         */
        public void PostPlaceShouldBeCreated(){
            //All fields are valid and no exception should therefore be thrown
            Assertions.assertDoesNotThrow(() -> p1 = new PostPlace("1234", "Månen",
                    "1377", "Melkeveien", "H"));
        }
    }
    @Nested
    class PostPlaceRegisterTest{
        @Test
        @DisplayName("Adding PostPlace to register. Should be added")
        /**
         * Adding a postPlace where it's postal code is not already in the register. Should therefore assert true
         */
        public void addingPostPlaceToRegister(){
            //There is no postPlace with 2045 as postalCode already in the register and p2 is therefore added
            Assertions.assertTrue(register.addPostPlace(p2));
        }

        @Test
        @DisplayName("Adding to PostPlaces with identical postalCodes. The last should return false")
        /**
         * Adding a postPlace where it's postal code is already in the register. Should therefore assert false
         */
        public void addingDuplicatePostalCodesShouldAssertFalse(){
            register.addPostPlace(p2);
            //p3's postalCode is identical to p2's which is already in the register and p3 is therefore
            //not added
            Assertions.assertFalse(register.addPostPlace(p3));
        }
    }

}
