package com.smartcity.gui;

import com.smartcity.dao.CityDAO;
import com.smartcity.dao.PlaceDAO;
import com.smartcity.dao.HotelDAO;

import com.smartcity.model.City;
import com.smartcity.model.Place;
import com.smartcity.model.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartCityGUI extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color SIDEBAR_COLOR =
            new Color(25, 35, 55);

    private final Color PRIMARY_COLOR =
            new Color(41, 128, 185);

    private final Color ACCENT_COLOR =
            new Color(46, 204, 113);

    private final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color TEXT_COLOR =
            new Color(40, 45, 55);

    private final Color SECONDARY_TEXT =
            new Color(110, 120, 135);


    // =========================================================
    // DAO OBJECTS
    // =========================================================

    private CityDAO cityDAO;
    private PlaceDAO placeDAO;
    private HotelDAO hotelDAO;


    // =========================================================
    // GUI COMPONENTS
    // =========================================================

    private JComboBox<String> cityComboBox;

    private JPanel placesPanel;
    private JPanel categoryPanel;

    private JTextArea detailsArea;

    private JLabel statusLabel;

    private JTextField hotelSearchField;

    private JComboBox<String> hotelSortBox;


    // =========================================================
    // DATABASE DATA
    // =========================================================

    private List<City> cities;

    private int selectedCityId = -1;


    // =========================================================
    // CATEGORIES
    // =========================================================

    private final String[] categories = {
            "Beach",
            "Entertainment",
            "Historical",
            "Museum",
            "Nature",
            "Religious"
    };


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SmartCityGUI() {

        cityDAO = new CityDAO();
        placeDAO = new PlaceDAO();
        hotelDAO = new HotelDAO();

        setTitle("Smart City Tourist Guide");

        setSize(1250, 750);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();

        loadCities();

        setVisible(true);
    }


    // =========================================================
    // CREATE GUI
    // =========================================================

    private void createGUI() {

        getContentPane().setBackground(
                BACKGROUND_COLOR
        );

        setLayout(
                new BorderLayout()
        );

        add(
                createSidebar(),
                BorderLayout.WEST
        );

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        mainPanel.add(
                createTopBar(),
                BorderLayout.NORTH
        );

        mainPanel.add(
                createDashboard(),
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }


    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(220, 700)
        );

        sidebar.setBackground(
                SIDEBAR_COLOR
        );


        JPanel logoPanel =
                new JPanel();

        logoPanel.setBackground(
                SIDEBAR_COLOR
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        30,
                        15,
                        25,
                        15
                )
        );

        JLabel logo =
                new JLabel("SMART CITY");

        logo.setForeground(
                Color.WHITE
        );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        logoPanel.add(logo);

        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );


        JPanel menuPanel =
                new JPanel();

        menuPanel.setBackground(
                SIDEBAR_COLOR
        );

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JButton homeButton =
                createMenuButton("Dashboard");

        JButton cityButton =
                createMenuButton("Cities");

        JButton placesButton =
                createMenuButton("Tourist Places");

        JButton hotelsButton =
                createMenuButton("Hotels");

        JButton categoryButton =
                createMenuButton("Categories");

        JButton aboutButton =
                createMenuButton("About");

        JButton exitButton =
                createMenuButton("Exit");


        homeButton.addActionListener(
                e -> goHome()
        );

        cityButton.addActionListener(
                e -> focusCitySelection()
        );

        placesButton.addActionListener(
                e -> showAllPlaces()
        );

        hotelsButton.addActionListener(
                e -> showHotels()
        );

        categoryButton.addActionListener(
                e -> focusCategories()
        );

        aboutButton.addActionListener(
                e -> showAbout()
        );

        exitButton.addActionListener(
                e -> exitApplication()
        );


        menuPanel.add(homeButton);
        menuPanel.add(Box.createVerticalStrut(8));

        menuPanel.add(cityButton);
        menuPanel.add(Box.createVerticalStrut(8));

        menuPanel.add(placesButton);
        menuPanel.add(Box.createVerticalStrut(8));

        menuPanel.add(hotelsButton);
        menuPanel.add(Box.createVerticalStrut(8));

        menuPanel.add(categoryButton);
        menuPanel.add(Box.createVerticalStrut(8));

        menuPanel.add(aboutButton);

        menuPanel.add(Box.createVerticalGlue());

        menuPanel.add(exitButton);

        menuPanel.add(
                Box.createVerticalStrut(20)
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        return sidebar;
    }


    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createMenuButton(String text) {

        JButton button =
                new JButton(text);

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                SIDEBAR_COLOR
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        12,
                        20,
                        12,
                        10
                )
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }


    // =========================================================
    // TOP BAR
    // =========================================================

    private JPanel createTopBar() {

        JPanel topBar =
                new JPanel(
                        new BorderLayout()
                );

        topBar.setBackground(
                Color.WHITE
        );

        topBar.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setBackground(
                Color.WHITE
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Smart City Tourist Guide"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(
                TEXT_COLOR
        );


        JLabel subtitle =
                new JLabel(
                        "Explore • Discover • Experience"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );


        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);


        topBar.add(
                titlePanel,
                BorderLayout.WEST
        );


        statusLabel =
                new JLabel("Database");

        statusLabel.setForeground(
                ACCENT_COLOR
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        topBar.add(
                statusLabel,
                BorderLayout.EAST
        );

        return topBar;
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    private JPanel createDashboard() {

        JPanel dashboard =
                new JPanel();

        dashboard.setBackground(
                BACKGROUND_COLOR
        );

        dashboard.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        dashboard.setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );


        // =====================================================
        // WELCOME CARD
        // =====================================================

        JPanel welcomeCard =
                new JPanel(
                        new BorderLayout()
                );

        welcomeCard.setBackground(
                PRIMARY_COLOR
        );

        welcomeCard.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );


        JPanel welcomeText =
                new JPanel();

        welcomeText.setBackground(
                PRIMARY_COLOR
        );

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel welcome =
                new JLabel(
                        "Welcome to Smart City"
                );

        welcome.setForeground(
                Color.WHITE
        );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );


        JLabel message =
                new JLabel(
                        "Discover amazing places around your city."
                );

        message.setForeground(
                Color.WHITE
        );

        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        welcomeText.add(welcome);

        welcomeText.add(
                Box.createVerticalStrut(8)
        );

        welcomeText.add(message);


        welcomeCard.add(
                welcomeText,
                BorderLayout.WEST
        );


        // =====================================================
        // CITY SELECTION
        // =====================================================

        JPanel citySelection =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        citySelection.setBackground(
                PRIMARY_COLOR
        );


        cityComboBox =
                new JComboBox<>();

        cityComboBox.setPreferredSize(
                new Dimension(
                        220,
                        38
                )
        );

        cityComboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        JButton exploreButton =
                new JButton("Explore");

        exploreButton.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        exploreButton.setBackground(
                ACCENT_COLOR
        );

        exploreButton.setForeground(
                Color.WHITE
        );

        exploreButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        exploreButton.setOpaque(true);

        exploreButton.setContentAreaFilled(true);

        exploreButton.setBorderPainted(false);

        exploreButton.setFocusPainted(false);

        exploreButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        exploreButton.addActionListener(
                e -> selectCity()
        );


        citySelection.add(cityComboBox);
        citySelection.add(exploreButton);


        welcomeCard.add(
                citySelection,
                BorderLayout.EAST
        );


        dashboard.add(
                welcomeCard,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerPanel.setBackground(
                BACKGROUND_COLOR
        );


        // =====================================================
        // CATEGORIES
        // =====================================================

        JPanel categoryContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        categoryContainer.setBackground(
                BACKGROUND_COLOR
        );


        JLabel categoryTitle =
                new JLabel(
                        "Explore Categories"
                );

        categoryTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        categoryTitle.setForeground(
                TEXT_COLOR
        );


        categoryContainer.add(
                categoryTitle,
                BorderLayout.NORTH
        );


        categoryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
                                10,
                                10
                        )
                );

        categoryPanel.setBackground(
                BACKGROUND_COLOR
        );


        createCategoryButtons();


        categoryContainer.add(
                categoryPanel,
                BorderLayout.CENTER
        );


        centerPanel.add(
                categoryContainer,
                BorderLayout.NORTH
        );


        // =====================================================
        // LOWER PANEL
        // =====================================================

        JPanel lowerPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                15
                        )
                );

        lowerPanel.setBackground(
                BACKGROUND_COLOR
        );


        placesPanel =
                new JPanel();

        placesPanel.setBackground(
                BACKGROUND_COLOR
        );

        placesPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );


        JScrollPane placesScroll =
                new JScrollPane(
                        placesPanel
                );

        placesScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Tourist Places / Hotels"
                )
        );


        // =====================================================
        // DETAILS
        // =====================================================

        detailsArea =
                new JTextArea();

        detailsArea.setEditable(false);

        detailsArea.setLineWrap(true);

        detailsArea.setWrapStyleWord(true);

        detailsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        detailsArea.setForeground(
                TEXT_COLOR
        );

        detailsArea.setBackground(
                Color.WHITE
        );

        detailsArea.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        detailsArea.setText(
                "Select a city to begin exploring."
        );


        JScrollPane detailsScroll =
                new JScrollPane(
                        detailsArea
                );

        detailsScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Details"
                )
        );


        lowerPanel.add(placesScroll);

        lowerPanel.add(detailsScroll);


        centerPanel.add(
                lowerPanel,
                BorderLayout.CENTER
        );


        dashboard.add(
                centerPanel,
                BorderLayout.CENTER
        );


        return dashboard;
    }


    // =========================================================
    // CATEGORY BUTTONS
    // =========================================================

    private void createCategoryButtons() {

        for (String category : categories) {

            JButton button =
                    new JButton(category);

            button.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            13
                    )
            );

            button.setBackground(
                    Color.WHITE
            );

            button.setForeground(
                    TEXT_COLOR
            );

            button.setOpaque(true);

            button.setContentAreaFilled(true);

            button.setBorderPainted(true);

            button.setFocusPainted(false);

            button.setBorder(
                    new LineBorder(
                            new Color(
                                    210,
                                    215,
                                    220
                            ),
                            1,
                            true
                    )
            );

            button.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            button.addActionListener(
                    e -> showPlaces(category)
            );

            categoryPanel.add(button);
        }
    }


    // =========================================================
    // LOAD CITIES
    // =========================================================

    private void loadCities() {

        cities =
                cityDAO.getAllCities();

        cityComboBox.removeAllItems();


        if (cities == null || cities.isEmpty()) {

            statusLabel.setText(
                    "Database Empty"
            );

            statusLabel.setForeground(
                    Color.RED
            );

            detailsArea.setText(
                    "No cities found in database.\n\n"
                    + "Please check your MySQL database."
            );

            return;
        }


        for (City city : cities) {

            cityComboBox.addItem(
                    city.getCityName()
                    + " - "
                    + city.getState()
            );
        }


        statusLabel.setText(
                "Database Connected"
        );

        statusLabel.setForeground(
                ACCENT_COLOR
        );
    }


    // =========================================================
    // SELECT CITY
    // =========================================================

    private void selectCity() {

        int index =
                cityComboBox.getSelectedIndex();


        if (index == -1 ||
                cities == null ||
                cities.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a city first!",
                    "City Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        City city =
                cities.get(index);


        selectedCityId =
                city.getCityId();


        detailsArea.setText(

                "CITY INFORMATION\n"
                + "════════════════════════════\n\n"

                + "City ID       : "
                + city.getCityId()
                + "\n\n"

                + "City          : "
                + city.getCityName()
                + "\n\n"

                + "State         : "
                + city.getState()
                + "\n\n"

                + "Choose a category above to "
                + "explore tourist places.\n\n"

                + "You can also select "
                + "\"Tourist Places\" or \"Hotels\" "
                + "from the left menu."
        );


        placesPanel.removeAll();

        placesPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );

        placesPanel.revalidate();

        placesPanel.repaint();
    }


    // =========================================================
    // SHOW PLACES BY CATEGORY
    // =========================================================

    private void showPlaces(String category) {

        if (selectedCityId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a city first!",
                    "City Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        List<Place> places =
                placeDAO.getPlacesByCity(
                        selectedCityId
                );


        placesPanel.removeAll();

        placesPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );


        boolean found = false;


        if (places != null) {

            for (Place place : places) {

                if (place.getCategory() != null
                        && place.getCategory()
                        .equalsIgnoreCase(category)) {

                    found = true;

                    placesPanel.add(
                            createPlaceCard(place)
                    );
                }
            }
        }


        if (!found) {

            JLabel emptyLabel =
                    new JLabel(
                            "No "
                            + category
                            + " places found.",
                            SwingConstants.CENTER
                    );

            emptyLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            emptyLabel.setForeground(
                    SECONDARY_TEXT
            );

            placesPanel.add(emptyLabel);
        }


        placesPanel.revalidate();

        placesPanel.repaint();


        detailsArea.setText(

                "CATEGORY\n"
                + "════════════════════════════\n\n"

                + category
                + "\n\n"

                + "Select a tourist place to "
                + "view complete information."
        );
    }


    // =========================================================
    // PLACE CARD
    // =========================================================

    private JPanel createPlaceCard(Place place) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                5
                        )
                );

        card.setBackground(
                CARD_COLOR
        );

        card.setBorder(
                new LineBorder(
                        new Color(
                                225,
                                230,
                                235
                        ),
                        1,
                        true
                )
        );


        JPanel info =
                new JPanel();

        info.setBackground(
                CARD_COLOR
        );

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        info.setBorder(
                new EmptyBorder(
                        12,
                        15,
                        12,
                        10
                )
        );


        JLabel name =
                new JLabel(
                        place.getPlaceName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        name.setForeground(
                TEXT_COLOR
        );


        JLabel location =
                new JLabel(
                        "Location: "
                        + place.getLocation()
                );

        location.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        location.setForeground(
                SECONDARY_TEXT
        );


        JLabel rating =
                new JLabel(
                        "Rating: "
                        + place.getRating()
                        + "     Entry Fee: Rs. "
                        + place.getEntryFee()
                );

        rating.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        rating.setForeground(
                PRIMARY_COLOR
        );


        info.add(name);

        info.add(
                Box.createVerticalStrut(5)
        );

        info.add(location);

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(rating);


        card.add(
                info,
                BorderLayout.CENTER
        );


        JButton viewButton =
                createPrimaryButton(
                        "View Details"
                );


        viewButton.addActionListener(
                e -> showPlaceDetails(place)
        );


        JPanel buttonPanel =
                new JPanel(
                        new GridBagLayout()
                );

        buttonPanel.setBackground(
                CARD_COLOR
        );

        buttonPanel.setBorder(
                new EmptyBorder(
                        10,
                        5,
                        10,
                        15
                )
        );

        buttonPanel.add(viewButton);


        card.add(
                buttonPanel,
                BorderLayout.EAST
        );


        return card;
    }


    // =========================================================
    // PLACE DETAILS
    // =========================================================

    private void showPlaceDetails(Place place) {

        Place selectedPlace =
                placeDAO.getPlaceById(
                        place.getPlaceId()
                );


        if (selectedPlace == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Place details not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        detailsArea.setText(

                "TOURIST PLACE\n"
                + "════════════════════════════\n\n"

                + "Place Name\n"
                + selectedPlace.getPlaceName()

                + "\n\n"

                + "Location\n"
                + selectedPlace.getLocation()

                + "\n\n"

                + "Rating\n"
                + selectedPlace.getRating()

                + "\n\n"

                + "Entry Fee\n"
                + "Rs. "
                + selectedPlace.getEntryFee()

                + "\n\n"

                + "Category\n"
                + selectedPlace.getCategory()

                + "\n\n"

                + "Description\n"
                + selectedPlace.getDescription()
        );
    }


    // =========================================================
    // SHOW HOTELS
    // =========================================================

    private void showHotels() {

        if (selectedCityId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a city first!",
                    "City Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        List<Hotel> hotels =
                hotelDAO.getHotelsByCity(
                        selectedCityId
                );


        placesPanel.removeAll();

        placesPanel.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        JPanel controlPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        controlPanel.setBackground(
                BACKGROUND_COLOR
        );


        hotelSearchField =
                new JTextField();

        hotelSearchField.setPreferredSize(
                new Dimension(
                        250,
                        35
                )
        );

        hotelSearchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        hotelSearchField.setToolTipText(
                "Search hotel by name"
        );


        hotelSortBox =
                new JComboBox<>(
                        new String[]{
                                "Default",
                                "Rating: High to Low",
                                "Price: Low to High",
                                "Price: High to Low"
                        }
                );

        hotelSortBox.setPreferredSize(
                new Dimension(
                        210,
                        35
                )
        );


        JLabel searchLabel =
                new JLabel("Search:");

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                0
                        )
                );

        searchPanel.setBackground(
                BACKGROUND_COLOR
        );

        searchPanel.add(searchLabel);

        searchPanel.add(hotelSearchField);


        JPanel sortPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                5,
                                0
                        )
                );

        sortPanel.setBackground(
                BACKGROUND_COLOR
        );


        JLabel sortLabel =
                new JLabel("Sort:");

        sortLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        sortPanel.add(sortLabel);

        sortPanel.add(hotelSortBox);


        controlPanel.add(
                searchPanel,
                BorderLayout.WEST
        );

        controlPanel.add(
                sortPanel,
                BorderLayout.EAST
        );


        JPanel hotelListPanel =
                new JPanel();

        hotelListPanel.setBackground(
                BACKGROUND_COLOR
        );

        hotelListPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );


        JScrollPane hotelScroll =
                new JScrollPane(
                        hotelListPanel
                );

        hotelScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );


        Runnable displayHotels =
                () -> {

                    hotelListPanel.removeAll();


                    String searchText =
                            hotelSearchField
                                    .getText()
                                    .trim()
                                    .toLowerCase();


                    List<Hotel> filteredHotels =
                            new ArrayList<>();


                    if (hotels != null) {

                        for (Hotel hotel : hotels) {

                            if (hotel.getHotelName() != null
                                    && hotel.getHotelName()
                                    .toLowerCase()
                                    .contains(searchText)) {

                                filteredHotels.add(hotel);
                            }
                        }
                    }


                    String sortOption =
                            (String)
                                    hotelSortBox
                                            .getSelectedItem();


                    if ("Rating: High to Low"
                            .equals(sortOption)) {

                        filteredHotels.sort(
                                Comparator
                                        .comparingDouble(
                                                Hotel::getRating
                                        )
                                        .reversed()
                        );

                    } else if ("Price: Low to High"
                            .equals(sortOption)) {

                        filteredHotels.sort(
                                Comparator
                                        .comparingDouble(
                                                Hotel::getPrice
                                        )
                        );

                    } else if ("Price: High to Low"
                            .equals(sortOption)) {

                        filteredHotels.sort(
                                Comparator
                                        .comparingDouble(
                                                Hotel::getPrice
                                        )
                                        .reversed()
                        );
                    }


                    if (filteredHotels.isEmpty()) {

                        JLabel noHotels =
                                new JLabel(
                                        "No hotels found.",
                                        SwingConstants.CENTER
                                );

                        noHotels.setFont(
                                new Font(
                                        "Arial",
                                        Font.BOLD,
                                        16
                                )
                        );

                        noHotels.setForeground(
                                SECONDARY_TEXT
                        );

                        hotelListPanel.add(noHotels);

                    } else {

                        for (Hotel hotel :
                                filteredHotels) {

                            hotelListPanel.add(
                                    createHotelCard(hotel)
                            );
                        }
                    }


                    hotelListPanel.revalidate();

                    hotelListPanel.repaint();
                };


        hotelSearchField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                displayHotels.run();
                            }

                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                displayHotels.run();
                            }

                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                displayHotels.run();
                            }
                        }
                );


        hotelSortBox.addActionListener(
                e -> displayHotels.run()
        );


        placesPanel.add(
                controlPanel,
                BorderLayout.NORTH
        );

        placesPanel.add(
                hotelScroll,
                BorderLayout.CENTER
        );


        displayHotels.run();


        placesPanel.revalidate();

        placesPanel.repaint();


        detailsArea.setText(

                "HOTELS\n"
                + "════════════════════════════\n\n"

                + "City: "
                + getSelectedCityName()

                + "\n\n"

                + "Hotels found: "
                + (hotels == null ? 0 : hotels.size())

                + "\n\n"

                + "You can:\n"
                + "• Search hotels by name\n"
                + "• Sort by rating\n"
                + "• Sort by price\n"
                + "• View hotel details"
        );
    }


    // =========================================================
    // HOTEL CARD
    // =========================================================

    private JPanel createHotelCard(Hotel hotel) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                new LineBorder(
                        new Color(
                                220,
                                225,
                                230
                        ),
                        1,
                        true
                )
        );


        JPanel info =
                new JPanel();

        info.setBackground(
                Color.WHITE
        );

        info.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        10
                )
        );

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel name =
                new JLabel(
                        hotel.getHotelName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        name.setForeground(
                TEXT_COLOR
        );


        JLabel location =
                new JLabel(
                        "Location: "
                        + hotel.getLocation()
                );

        location.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        location.setForeground(
                SECONDARY_TEXT
        );


        JLabel rating =
                new JLabel(
                        "Rating: "
                        + String.format(
                                "%.1f",
                                hotel.getRating()
                        )
                        + " / 5"
                );

        rating.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        rating.setForeground(
                PRIMARY_COLOR
        );


        JLabel price =
                new JLabel(
                        "Price: Rs. "
                        + String.format(
                                "%.2f",
                                hotel.getPrice()
                        )
                        + " / night"
                );

        price.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        price.setForeground(
                ACCENT_COLOR
        );


        info.add(name);

        info.add(
                Box.createVerticalStrut(6)
        );

        info.add(location);

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(rating);

        info.add(
                Box.createVerticalStrut(5)
        );

        info.add(price);


        card.add(
                info,
                BorderLayout.CENTER
        );


        JButton viewButton =
                createPrimaryButton(
                        "View Details"
                );

        viewButton.addActionListener(
                e -> showHotelDetails(hotel)
        );


        JPanel buttonPanel =
                new JPanel(
                        new GridBagLayout()
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        buttonPanel.setBorder(
                new EmptyBorder(
                        10,
                        5,
                        10,
                        15
                )
        );

        buttonPanel.add(viewButton);


        card.add(
                buttonPanel,
                BorderLayout.EAST
        );


        return card;
    }


    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        button.setBackground(
                PRIMARY_COLOR
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }


    // =========================================================
    // HOTEL DETAILS
    // =========================================================

    private void showHotelDetails(
            Hotel hotel) {

        Hotel selectedHotel =
                hotelDAO.getHotelById(
                        hotel.getHotelId()
                );


        if (selectedHotel == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Hotel details not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        detailsArea.setText(

                "HOTEL DETAILS\n"
                + "════════════════════════════\n\n"

                + "Hotel Name\n"
                + selectedHotel.getHotelName()

                + "\n\n"

                + "Location\n"
                + selectedHotel.getLocation()

                + "\n\n"

                + "Rating\n"
                + selectedHotel.getRating()
                + " / 5"

                + "\n\n"

                + "Price\n"
                + "Rs. "
                + selectedHotel.getPrice()
                + " / night"

                + "\n\n"

                + "Hotel ID\n"
                + selectedHotel.getHotelId()

                + "\n\n"

                + "City ID\n"
                + selectedHotel.getCityId()
        );
    }


    // =========================================================
    // GET SELECTED CITY NAME
    // =========================================================

    private String getSelectedCityName() {

        if (cities == null ||
                selectedCityId == -1) {

            return "selected city";
        }


        for (City city : cities) {

            if (city.getCityId()
                    == selectedCityId) {

                return city.getCityName();
            }
        }


        return "selected city";
    }


    // =========================================================
    // SHOW ALL PLACES
    // =========================================================

    private void showAllPlaces() {

        if (selectedCityId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a city first!",
                    "City Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        List<Place> places =
                placeDAO.getPlacesByCity(
                        selectedCityId
                );


        placesPanel.removeAll();


        placesPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );


        if (places == null ||
                places.isEmpty()) {

            JLabel label =
                    new JLabel(
                            "No tourist places found.",
                            SwingConstants.CENTER
                    );

            label.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            label.setForeground(
                    SECONDARY_TEXT
            );

            placesPanel.add(label);

        } else {

            for (Place place : places) {

                placesPanel.add(
                        createPlaceCard(place)
                );
            }
        }


        placesPanel.revalidate();

        placesPanel.repaint();


        detailsArea.setText(

                "ALL TOURIST PLACES\n"
                + "════════════════════════════\n\n"

                + "Showing all tourist places "
                + "available in "

                + getSelectedCityName()

                + "."
        );
    }


    // =========================================================
    // HOME
    // =========================================================

    private void goHome() {

        selectedCityId = -1;


        if (cityComboBox.getItemCount() > 0) {

            cityComboBox.setSelectedIndex(0);
        }


        placesPanel.removeAll();


        placesPanel.setLayout(
                new GridLayout(
                        0,
                        1,
                        10,
                        10
                )
        );


        detailsArea.setText(

                "Welcome to Smart City Tourist Guide!\n\n"

                + "Select a city and start exploring.\n\n"

                + "You can explore:\n"

                + "• Tourist Places\n"
                + "• Hotels\n"
                + "• Categories"
        );


        placesPanel.revalidate();

        placesPanel.repaint();
    }


    // =========================================================
    // CITY MENU
    // =========================================================

    private void focusCitySelection() {

        cityComboBox.requestFocus();


        detailsArea.setText(

                "CITY SELECTION\n\n"

                + "Choose a city from the dropdown "
                + "and click Explore."
        );
    }


    // =========================================================
    // CATEGORY MENU
    // =========================================================

    private void focusCategories() {

        detailsArea.setText(

                "TOURIST CATEGORIES\n\n"

                + "Choose one of the categories "
                + "to explore tourist places."
        );
    }


    // =========================================================
    // ABOUT
    // =========================================================

    private void showAbout() {

        JOptionPane.showMessageDialog(

                this,

                "SMART CITY TOURIST GUIDE\n\n"

                + "A Java Swing based tourism application.\n\n"

                + "Features:\n"

                + "• Dynamic city selection\n"
                + "• Tourist categories\n"
                + "• Tourist place details\n"
                + "• Hotel information\n"
                + "• Hotel search\n"
                + "• Hotel sorting\n"
                + "• Ratings and prices\n"
                + "• MySQL database integration\n"
                + "• Modern dashboard interface\n\n"

                + "Developed using:\n"

                + "Java Swing + MySQL",

                "About Smart City",

                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // EXIT
    // =========================================================

    private void exitApplication() {

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to exit?",

                        "Exit Application",

                        JOptionPane.YES_NO_OPTION
                );


        if (choice ==
                JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );

                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                    new SmartCityGUI();
                }
        );
    }
}

