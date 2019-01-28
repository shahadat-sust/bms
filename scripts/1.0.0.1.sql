-- -----------------------------------------------------------------------------------------------------------------
-- Create User
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO User (Id, Username, Password, IsActive, Status, CreatedOn, UpdatedOn) VALUES 
(1, 'sourceenginesltd', '0OrTwH/chnK1BJurfqQItg==', 1, 1, CURDATE(), CURDATE());

INSERT INTO UserProfile (Id, UserId, FirstName, LastName, Gender, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 1, 'Source', 'Engines', 1, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Group
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO `Group` (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Admin', 1, CURDATE(), 1, CURDATE()),
(2, 'Provider', 1, CURDATE(), 1, CURDATE()),
(3, 'Consumer', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Role
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO Role (Id, Name, Priority, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Super User', 1, 1, CURDATE(), 1, CURDATE()),
(2, 'Master User', 2, 1, CURDATE(), 1, CURDATE()),
(3, 'Administrator', 3, 1, CURDATE(), 1, CURDATE()),
(4, 'Consumer', 999, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create UserGroup
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO UserGroup (UserId, GroupId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 1, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create UserRole
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO UserRole (UserId, RoleId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 1, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Country
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO Country (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES
(1, 'Bangladesh', 1, CURDATE(), 1, CURDATE()),
(2, 'India', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create State
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO State (Id, Name, CountryId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES
(1, 'Dhaka', 1, 1, CURDATE(), 1, CURDATE()),
(2, 'Chittagong', 1, 1, CURDATE(), 1, CURDATE()),
(3, 'Sylhet', 1, 1, CURDATE(), 1, CURDATE()),
(4, 'Goa', 2, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create City
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO City (Id, Name, StateId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES
(1, 'Dhaka', 1, 1, CURDATE(), 1, CURDATE()),
(2, 'Cox''s Bazar', 2, 1, CURDATE(), 1, CURDATE()),
(3, 'Srimongol', 3, 1, CURDATE(), 1, CURDATE()),
(4, 'Goa', 4, 1, CURDATE(), 1, CURDATE());


-- -----------------------------------------------------------------------------------------------------------------
-- Create Provider Type
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO ProviderType (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Hotel', 1, CURDATE(), 1, CURDATE()),
(2, 'Rent A Car', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Booking Type
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO BookingType (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Online', 1, CURDATE(), 1, CURDATE()),
(2, 'Onsite', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Booking Status
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO BookingStatus (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Booked', 1, CURDATE(), 1, CURDATE()),
(2, 'Pending', 1, CURDATE(), 1, CURDATE()),
(3, 'Cancelled', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Payment Type
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO PaymentType (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Cash', 1, CURDATE(), 1, CURDATE()),
(2, 'Card', 1, CURDATE(), 1, CURDATE()),
(3, 'Mobile Banking', 1, CURDATE(), 1, CURDATE()),
(4, 'Payment Platform', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Payment Method
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO PaymentMethod (Id, Name, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES 
(1, 'Master', 1, CURDATE(), 1, CURDATE()),
(2, 'Visa', 1, CURDATE(), 1, CURDATE()),
(3, 'Rocket', 1, CURDATE(), 1, CURDATE()),
(4, 'bKash', 1, CURDATE(), 1, CURDATE()),
(5, 'PayPal', 1, CURDATE(), 1, CURDATE()),
(6, 'iPal', 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Point Of Interest
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO PointOfInterest (Id, Name, ProviderTypeId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES
(1, 'Swimming Pool', 1, 1, CURDATE(), 1, CURDATE()),
(2, 'Parking', 1, 1, CURDATE(), 1, CURDATE());

-- -----------------------------------------------------------------------------------------------------------------
-- Create Point Of Interest
-- -----------------------------------------------------------------------------------------------------------------
INSERT INTO Amenity (Id, Name, Type, ProviderTypeId, CreatedBy, CreatedOn, UpdatedBy, UpdatedOn) VALUES
(1, 'Television', 0, 1, 1, CURDATE(), 1, CURDATE()),
(2, 'Lan Phone', 0, 1, 1, CURDATE(), 1, CURDATE()),
(3, 'Air Conditioner', 0, 1, 1, CURDATE(), 1, CURDATE());


