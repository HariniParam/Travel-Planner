CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45),
    password VARCHAR(45)
);

CREATE TABLE package (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    package_name VARCHAR(255) NOT NULL,
    package_description TEXT,
    destination VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    inclusions TEXT,
    exclusions TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    available_seats INT NOT NULL,
    package_images VARCHAR(255), 
    average_rating DECIMAL(3, 2),
    contactEmail VARCHAR(255),
    contactPhone VARCHAR(20)
);

CREATE TABLE cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    package_id INT,
    quantity INT
);

-- Insert the first package
INSERT INTO package (package_id,package_name, package_description, destination, duration, price, inclusions, exclusions, start_date,end_date, available_seats, package_images, average_rating, ratings_and_reviews)
VALUES (
	 1,
    'Package 1',
    'Explore the beautiful beaches of Bali.',
    'Bali',
    7,
    1200.00,
    'Accommodation, Airport transfers, Guided tours',
    'Airfare, Meals',
    "2023-10-01", "2023-10-08",
    20,
    "bali1.jpg",
    4.8,
    "JohnDoeTravel@example.com",
    "+91-9876543210"
);

-- Insert the second package
INSERT INTO package (package_id,package_name, package_description, destination, duration, price, inclusions, exclusions, start_date,end_date, available_seats, package_images, average_rating, ratings_and_reviews)
VALUES (
	 2,
    'Package 2',
    'Discover the magic of Rome and its history.',
    'Rome',
    5,
    1600.00,
    'Accommodation, Guided tours, Meals',
    'Airfare, Airport transfers',
    "2023-09-15",  "2023-09-20",
    15,
    "rome1.jpg",
    4.5,
    "SarahAdventures@gmail.com",
    "+91-8765432109"
);

-- Insert the third package
INSERT INTO package (package_id,package_name, package_description, destination, duration, price, inclusions, exclusions, start_date,end_date, available_seats, package_images, average_rating, ratings_and_reviews)
VALUES (
	 3, 
    'Package 3',
    'Safari adventure in South Africa.',
    'South Africa',
    10,
    2800.00,
    'Accommodation, Safari tours, Meals',
    'Airfare, Airport transfers',
    "2023-11-10", "2023-11-20",
    12,
    "safari1.jpg",
    4.9,
    "ExploreWithMaria@hotmail.com",
    "+91-7654321098"
);

--Insert the fourth package
INSERT INTO package (package_id,package_name, package_description, destination, duration, price, inclusions, exclusions, start_date, end_date, available_seats, package_images, average_rating, ratings_and_reviews)
VALUES (
	4,
    'Package 4',
    'Explore the historic streets of Kyoto.',
    'Kyoto',
    6,
    1400.00,
    'Accommodation, Guided tours, Cultural experiences',
    'Airfare, Meals',
    "2023-12-05","2023-12-10",
    18,
    'kyoto1.jpg',
    4.6,
    "AdventureSeekers@example.com",
    "+91-6543210987"
);

