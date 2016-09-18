package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;
import json.DataStorage;
import logic.GraphController;
import logic.UnitController;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.google.gson.JsonIOException;

public class GUI extends JFrame{

//ALL VARIABLE DECLARATIONS
	
		//InputPanel
		JLabel inputChar;
		JLabel inputRoute;
		JLabel inputLevel;
		JLabel inputHP;
		JLabel inputStr;
		JLabel inputMag;
		JLabel inputSkl;
		JLabel inputSpd;
		JLabel inputLck;
		JLabel inputDef;
		JLabel inputRes;
	
		JComboBox inputCharBox;
		JComboBox inputRouteBox;
		JComboBox inputLevelBox;

		JTextField inputHPField;
		JTextField inputStrField;
		JTextField inputMagField;
		JTextField inputSklField;
		JTextField inputSpdField;
		JTextField inputLckField;
		JTextField inputDefField;
		JTextField inputResField;

//ResultPanel
		JComboBox resultLevelBox;
		JLabel resultClassLabel;
		JLabel resultClassDisplay;
		
		JLabel resultHP;
		JLabel resultStr;
		JLabel resultMag;
		JLabel resultSkl;
		JLabel resultSpd;
		JLabel resultLck;
		JLabel resultDef;
		JLabel resultRes;
		
		JTextField resultHPField;
		JTextField resultStrField;
		JTextField resultMagField;
		JTextField resultSklField;
		JTextField resultSpdField;
		JTextField resultLckField;
		JTextField resultDefField;
		JTextField resultResField;

		JTextField avgHPField;
		JTextField avgStrField;
		JTextField avgMagField;
		JTextField avgSklField;
		JTextField avgSpdField;
		JTextField avgLckField;
		JTextField avgDefField;
		JTextField avgResField;
		
		JTextField resultHPDifference;
		JTextField resultStrDifference;
		JTextField resultMagDifference;
		JTextField resultSklDifference;
		JTextField resultSpdDifference;
		JTextField resultLckDifference;
		JTextField resultDefDifference;
		JTextField resultResDifference;
		
//GraphPanel
		JLabel graphStat;
		JComboBox graphStatBox;
		JButton calculateButton;
		JButton optionsButton;
		JButton clearButton;
		
//Option Window
		JDialog optionPane;
		JButton promoteButton;
		JButton parentalUnitsButton;
		JButton confirmButton;
		JButton eternalSealButton;
		
		JList jobHistory;
		
		JLabel avatarBoonLabel;
		JLabel avatarBaneLabel;
		JComboBox avatarBoonBox;
		JComboBox avatarBaneBox;
		
		JLabel reclassLabel;
		JComboBox reclassBox;
		JButton reclassButton;
		
//ParentalUnitsPane
		JDialog parentalUnitsPane;
		
		JLabel fixedParentName;
		JLabel fixedParentNameDisplay;
		JLabel fixedParentClass;
		JComboBox fixedParentClassDisplay;
		JLabel fixedParentHP;
		JLabel fixedParentStr;
		JLabel fixedParentMag;
		JLabel fixedParentSkl;
		JLabel fixedParentSpd;
		JLabel fixedParentLck;
		JLabel fixedParentDef;
		JLabel fixedParentRes;
		JTextField fixedParentHPField;
		JTextField fixedParentStrField;
		JTextField fixedParentMagField;
		JTextField fixedParentSklField;
		JTextField fixedParentSpdField;
		JTextField fixedParentLckField;
		JTextField fixedParentDefField;
		JTextField fixedParentResField;
		
		JLabel variedParentName;
		JComboBox variedParentNameDisplay;
		JLabel variedParentClass;
		JComboBox variedParentClassDisplay;
		JComboBox childStartingLevelBox;
		
		JLabel variedParentHP;
		JLabel variedParentStr;
		JLabel variedParentMag;
		JLabel variedParentSkl;
		JLabel variedParentSpd;
		JLabel variedParentLck;
		JLabel variedParentDef;
		JLabel variedParentRes;
		JTextField variedParentHPField;
		JTextField variedParentStrField;
		JTextField variedParentMagField;
		JTextField variedParentSklField;
		JTextField variedParentSpdField;
		JTextField variedParentLckField;
		JTextField variedParentDefField;
		JTextField variedParentResField;
		
		JLabel boonLabel;
		JLabel baneLabel;
		JComboBox boonBox;
		JComboBox baneBox;
		JButton parentalConfirm;
		JButton parentalCancel;

		JFreeChart lineChart;
	//promoteWindow
	//EternalSeal window mayyybe?
		
//OTHER STUFF
static String[] routes = {"Conquest", "Birthright", "Revelations"};

static String[] conquestCharacters;
static String[] birthrightCharacters;
static String[] revelationsCharacters;

static String[] jobs;

public static void main(String[]args)
{
	new GUI();
}

//============================================================START GUI CLASS======================================================
public GUI()
{	
	GraphController graphcontroller = GraphController.getInstance();
	UnitController unitcontroller = UnitController.getInstance();
	DataStorage data = DataStorage.getInstance();
	data.ParseJsonCharacters();
	data.ParseJsonJobs();
	data.InitializeBoonsAndBanes();
	
	conquestCharacters = data.getConquestCharacters().toArray(new String[0]);
	birthrightCharacters = data.getBirthrightCharacters().toArray(new String[0]);
	revelationsCharacters = data.getRevelationsCharacters().toArray(new String[0]);
	jobs = data.getJobNames().toArray(new String[0]);
	
	//Main Panel
	JPanel mainPanel = new JPanel();
	
	//Input Panel 1 (Contains Character, Job, and Route modifiers)
	JPanel inputPanel1 = new JPanel();
	
		inputRoute = new JLabel("Route: ");
		inputRouteBox = new JComboBox<String>(routes);
		ComboBoxHandler ComboBoxHandler = new ComboBoxHandler();
		inputRouteBox.addActionListener(ComboBoxHandler);
			inputPanel1.add(inputRoute);
			inputPanel1.add(inputRouteBox);
		
		inputChar = new JLabel("Char: ");
		inputCharBox = new JComboBox<String>(conquestCharacters);
		inputCharBox.setPreferredSize(new Dimension(100,25));
		CharBoxHandler CharBoxHandler = new CharBoxHandler();
		inputCharBox.addActionListener(CharBoxHandler);
			inputPanel1.add(inputChar);
			inputPanel1.add(inputCharBox);
								
		inputLevel = new JLabel("Level: ");
		inputLevelBox = new JComboBox<Integer>();
		InputLevelBoxHandler inputlevelboxhandler = new InputLevelBoxHandler();
		inputLevelBox.addActionListener(inputlevelboxhandler);
			inputPanel1.add(inputLevel);
			inputPanel1.add(inputLevelBox);
				inputLevel.setVisible(false);
				inputLevelBox.setVisible(false);

		parentalUnitsButton = new JButton("Child Options");
		ParentalUnitsButtonHandler parentalUnitsButtonHandler = new ParentalUnitsButtonHandler();
			parentalUnitsButton.addActionListener(parentalUnitsButtonHandler);
			inputPanel1.add(parentalUnitsButton);
				parentalUnitsButton.setVisible(false);

	//Input Panel 2 (Contains all the stat mods)
	JPanel inputPanel2 = new JPanel();
	inputPanel2.setLayout(new GridLayout(4,4));
	
		inputHP = new JLabel("HP: ");
		inputHPField = new JTextField(" ");
			inputPanel2.add(inputHP);
			inputPanel2.add(inputHPField);	
	
		inputSpd = new JLabel("Spd: ");
		inputSpdField = new JTextField(" ");
			inputPanel2.add(inputSpd);
			inputPanel2.add(inputSpdField);		
			
		inputStr = new JLabel("Str: ");
		inputStrField = new JTextField(" ");
			inputPanel2.add(inputStr);
			inputPanel2.add(inputStrField);	
			
		inputLck = new JLabel("Lck: ");
		inputLckField = new JTextField(" ");
			inputPanel2.add(inputLck);
			inputPanel2.add(inputLckField);				
			
		inputMag = new JLabel("Mag: ");
		inputMagField = new JTextField(" ");
			inputPanel2.add(inputMag);
			inputPanel2.add(inputMagField);
			
		inputDef = new JLabel("Def: ");
		inputDefField = new JTextField(" ");
			inputPanel2.add(inputDef);
			inputPanel2.add(inputDefField);			
			
		inputSkl = new JLabel("Skl: ");
		inputSklField = new JTextField(" ");
			inputPanel2.add(inputSkl);
			inputPanel2.add(inputSklField);	
			
		inputRes = new JLabel("Res: ");
		inputResField = new JTextField(" ");
			inputPanel2.add(inputRes);
			inputPanel2.add(inputResField);	
			
	//Result Panel
	JPanel resultPanel = new JPanel();
	resultPanel.setLayout(new GridLayout(5,4));
	
	JLabel blank1 = new JLabel("");
	JLabel YourStats1 = new JLabel("Your Val");
	JLabel AvgStats1 = new JLabel("Avg. Val");
	JLabel Difference1 = new JLabel("Difference");
		resultPanel.add(blank1);
		resultPanel.add(YourStats1);
		resultPanel.add(AvgStats1);
		resultPanel.add(Difference1);

	JLabel blank2 = new JLabel("");
	JLabel YourStats2 = new JLabel("Your Val");
	JLabel AvgStats2 = new JLabel("Avg. Val");
	JLabel Difference2 = new JLabel("Difference");
		resultPanel.add(blank2);
		resultPanel.add(YourStats2);
		resultPanel.add(AvgStats2);
		resultPanel.add(Difference2);
		
		resultHP = new JLabel("HP: ");
		resultHPField = new JTextField(" ");
		resultHPField.setEditable(false);
		avgHPField = new JTextField(" ");
		avgHPField.setEditable(false);
		resultHPDifference = new JTextField(" ");
		resultHPDifference.setEditable(false);
			resultHPField.setBackground(Color.WHITE);
			avgHPField.setBackground(Color.WHITE);
			resultHPDifference.setBackground(Color.WHITE);
			resultPanel.add(resultHP);
			resultPanel.add(resultHPField);	
			resultPanel.add(avgHPField);	
			resultPanel.add(resultHPDifference);
		
		resultSpd = new JLabel("Spd: ");
		resultSpdField = new JTextField(" ");
		resultSpdField.setEditable(false);
		avgSpdField = new JTextField(" ");
		avgSpdField.setEditable(false);
		resultSpdDifference = new JTextField(" ");
		resultSpdDifference.setEditable(false);
			resultSpdField.setBackground(Color.WHITE);
			avgSpdField.setBackground(Color.WHITE);
			resultSpdDifference.setBackground(Color.WHITE);
			resultPanel.add(resultSpd);
			resultPanel.add(resultSpdField);	
			resultPanel.add(avgSpdField);	
			resultPanel.add(resultSpdDifference);
		
		resultStr = new JLabel("Str: ");
		resultStrField = new JTextField(" ");
		resultStrField.setEditable(false);
		avgStrField = new JTextField(" ");
		avgStrField.setEditable(false);
		resultStrDifference = new JTextField(" ");
		resultStrDifference.setEditable(false);
			resultStrField.setBackground(Color.WHITE);
			avgStrField.setBackground(Color.WHITE);
			resultStrDifference.setBackground(Color.WHITE);
			resultPanel.add(resultStr);
			resultPanel.add(resultStrField);	
			resultPanel.add(avgStrField);	
			resultPanel.add(resultStrDifference);

		resultLck = new JLabel("Lck: ");
		resultLckField = new JTextField(" ");
		resultLckField.setEditable(false);
		avgLckField = new JTextField(" ");
		avgLckField.setEditable(false);
		resultLckDifference = new JTextField(" ");
		resultLckDifference.setEditable(false);
			resultLckField.setBackground(Color.WHITE);
			avgLckField.setBackground(Color.WHITE);
			resultLckDifference.setBackground(Color.WHITE);
			resultPanel.add(resultLck);
			resultPanel.add(resultLckField);	
			resultPanel.add(avgLckField);	
			resultPanel.add(resultLckDifference);			
		
		resultMag = new JLabel("Mag: ");
		resultMagField = new JTextField(" ");
		resultMagField.setEditable(false);
		avgMagField = new JTextField(" ");
		avgMagField.setEditable(false);
		resultMagDifference = new JTextField(" ");
		resultMagDifference.setEditable(false);
			resultMagField.setBackground(Color.WHITE);
			avgMagField.setBackground(Color.WHITE);
			resultMagDifference.setBackground(Color.WHITE);
			resultPanel.add(resultMag);
			resultPanel.add(resultMagField);	
			resultPanel.add(avgMagField);	
			resultPanel.add(resultMagDifference);			
		
		resultDef = new JLabel("Def: ");
		resultDefField = new JTextField(" ");
		resultDefField.setEditable(false);
		avgDefField = new JTextField(" ");
		avgDefField.setEditable(false);
		resultDefDifference = new JTextField(" ");
		resultDefDifference.setEditable(false);
			resultDefField.setBackground(Color.WHITE);
			avgDefField.setBackground(Color.WHITE);
			resultDefDifference.setBackground(Color.WHITE);
			resultPanel.add(resultDef);
			resultPanel.add(resultDefField);	
			resultPanel.add(avgDefField);	
			resultPanel.add(resultDefDifference);				

		resultSkl= new JLabel("Skl: ");
		resultSklField = new JTextField(" ");
		resultSklField.setEditable(false);
		avgSklField = new JTextField(" ");
		avgSklField.setEditable(false);
		resultSklDifference = new JTextField(" ");
		resultSklDifference.setEditable(false);
			resultSklField.setBackground(Color.WHITE);
			avgSklField.setBackground(Color.WHITE);
			resultSklDifference.setBackground(Color.WHITE);
			resultPanel.add(resultSkl);
			resultPanel.add(resultSklField);	
			resultPanel.add(avgSklField);	
			resultPanel.add(resultSklDifference);	
			
		resultRes= new JLabel("Res: ");
		resultResField = new JTextField(" ");
		resultResField.setEditable(false);
		avgResField = new JTextField(" ");
		avgResField.setEditable(false);
		resultResDifference = new JTextField(" ");
		resultResDifference.setEditable(false);
			resultResField.setBackground(Color.WHITE);
			avgResField.setBackground(Color.WHITE);
			resultResDifference.setBackground(Color.WHITE);
			resultPanel.add(resultRes);
			resultPanel.add(resultResField);	
			resultPanel.add(avgResField);	
			resultPanel.add(resultResDifference);	
	
	//GRAPH PANEL 1
	JPanel graphPanel = new JPanel();
		graphStat = new JLabel("Stat: ");
		String[] statArray = {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
		graphStatBox = new JComboBox(statArray);
		graphBoxHandler graphboxhandler = new graphBoxHandler();
		graphStatBox.addActionListener(graphboxhandler);
			graphPanel.add(graphStat);
			graphPanel.add(graphStatBox);
		graphStatBox.setEnabled(false);

	//GRAPH PANEL 2
	JPanel graphPanel2 = new JPanel();
	Border graphBorder2 = BorderFactory.createTitledBorder("Graph");
	graphPanel2.setBorder(graphBorder2);
	
	//Organize minipanels
	
	JPanel mainInputPanel = new JPanel();
	
	Border inputBorder = BorderFactory.createTitledBorder("Input");
		mainInputPanel.setLayout(new BoxLayout(mainInputPanel, BoxLayout.PAGE_AXIS));
		mainInputPanel.setBorder(inputBorder);
			mainInputPanel.add(inputPanel1);
			mainInputPanel.add(inputPanel2);
	Border resultBorder = BorderFactory.createTitledBorder("Results");
		resultPanel.setBorder(resultBorder);

	JPanel leftSide = new JPanel();
	leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.PAGE_AXIS));
		leftSide.add(mainInputPanel);
	JPanel optionsButtonPanel = new JPanel();
		optionsButton = new JButton("Character Options");
		OpenOptionButtonHandler OptionButtonHandler = new OpenOptionButtonHandler();
		optionsButton.addActionListener(OptionButtonHandler);
			optionsButtonPanel.add(optionsButton);
		resultLevelBox = new JComboBox();
		resultLevelBox.setEnabled(false);
			ResultLevelBoxHandler resultlevelboxhandler = new ResultLevelBoxHandler();
			resultLevelBox.addActionListener(resultlevelboxhandler);
		resultClassLabel = new JLabel("Class: ");
		resultClassDisplay = new JLabel("Error");
			optionsButtonPanel.add(resultLevelBox);
			optionsButtonPanel.add(resultClassLabel);
			optionsButtonPanel.add(resultClassDisplay);
		leftSide.add(optionsButtonPanel);
		leftSide.add(resultPanel);
	
	JPanel rightSide = new JPanel();
	rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.PAGE_AXIS));
	Border rightBorder= BorderFactory.createTitledBorder("Graph Results");
		rightSide.setBorder(rightBorder);
		rightSide.add(graphPanel);
		rightSide.add(graphPanel2);
	
//GRAPH PANEL BUTTONS
	JPanel buttonPanel = new JPanel();
	
	clearButton = new JButton("Clear");	
		ClearButtonHandler clearbuttonhandler = new ClearButtonHandler();
	clearButton.addActionListener(clearbuttonhandler);
	calculateButton = new JButton("Calculate!");
		CalculateButtonHandler CalculateButtonHandler = new CalculateButtonHandler();
	calculateButton.addActionListener(CalculateButtonHandler);
		buttonPanel.add(clearButton);
		buttonPanel.add(calculateButton);
		rightSide.add(buttonPanel);
		
	mainPanel.add(leftSide);
	mainPanel.add(rightSide);
	
	//Main Window
	this.setSize(500,800);
	this.setTitle("Blessed or Screwed!");
	this.setResizable(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.add(mainPanel);
	this.setVisible(true);

//CHARACTER OPTIONS DIALOG
	optionPane = new JDialog(this, true);
	
	//ListPanel
	JPanel listPanel = new JPanel();
	Border listBorder = BorderFactory.createTitledBorder("Job History");
	listPanel.setBorder(listBorder);

//Initializes Job History
	jobHistory = new JList();
	jobHistory.setFixedCellWidth(150);
	jobHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
	listPanel.add(new JScrollPane(jobHistory));

//ModifierPanel
	JPanel modPanel = new JPanel();
	Border modBorder = BorderFactory.createTitledBorder("Options");
		modPanel.setBorder(modBorder);
	modPanel.setLayout(new BoxLayout(modPanel, BoxLayout.PAGE_AXIS));

//Boon and Bane Panel
	JPanel avatarBoonBanePanel = new JPanel();
	avatarBoonLabel = new JLabel("Boons:");
	avatarBoonBox= new JComboBox(new DefaultComboBoxModel(data.getBoons()));
		avatarBoonBox.setSelectedIndex(Arrays.asList(data.getBoons()).indexOf("Strong (Str)"));
	avatarBaneLabel = new JLabel("Banes:");
	avatarBaneBox= new JComboBox(new DefaultComboBoxModel(data.getBanes()));
		avatarBaneBox.setSelectedIndex(Arrays.asList(data.getBanes()).indexOf("Fragile (Def)"));
		avatarBoonBox.setEnabled(false);
		avatarBaneBox.setEnabled(false);
		avatarBoonBanePanel.add(avatarBoonLabel);
		avatarBoonBanePanel.add(avatarBoonBox);
		avatarBoonBanePanel.add(avatarBaneLabel);
		avatarBoonBanePanel.add(avatarBaneBox);
			modPanel.add(avatarBoonBanePanel);
			
//Reclass Panel	
	JPanel reclassPanel = new JPanel();
	reclassLabel = new JLabel("Reclass:");
		reclassPanel.add(reclassLabel);
	reclassBox = new JComboBox();
	reclassBox.setModel(new DefaultComboBoxModel(jobs));
		reclassPanel.add(reclassBox);		
	reclassButton = new JButton("Reclass");
		ReclassOptionButtonHandler ReclassOptionButtonHandler = new ReclassOptionButtonHandler();
		reclassButton.addActionListener(ReclassOptionButtonHandler);
			reclassPanel.add(reclassButton);	
	modPanel.add(reclassPanel);

//Button Panel
	JPanel buttonOptionsPanel = new JPanel();
	buttonOptionsPanel.setLayout(new GridLayout(2,2));
		promoteButton = new JButton("Promote");
			PromoteOptionButtonHandler PromoteOptionButtonHandler = new PromoteOptionButtonHandler();
			promoteButton.addActionListener(PromoteOptionButtonHandler);
			buttonOptionsPanel.add(promoteButton);
				promoteButton.setEnabled(false);
		eternalSealButton = new JButton("EternalSeal");
			EternalSealButtonHandler EternalSealButtonHandler = new EternalSealButtonHandler();
			eternalSealButton.addActionListener(EternalSealButtonHandler);
			buttonOptionsPanel.add(eternalSealButton);
				eternalSealButton.setEnabled(false);
		confirmButton = new JButton("Confirm");
			CloseOptionButtonHandler CloseOptionButtonHandler = new CloseOptionButtonHandler();
			confirmButton.addActionListener(CloseOptionButtonHandler);
			buttonOptionsPanel.add(confirmButton);
			
	modPanel.add(buttonOptionsPanel);
	
//PARENTAL UNITS PANEL
	parentalUnitsPane  = new JDialog(this, true);
	JPanel parentalUnitsPanel = new JPanel();
	
//These panels are for fixed parent
	JPanel fixedParentPanelMain = new JPanel();
	Border fixedParentPanelBorder = BorderFactory.createTitledBorder("Fixed Parent");
	fixedParentPanelMain.setBorder(fixedParentPanelBorder);
	fixedParentPanelMain.setLayout(new BoxLayout(fixedParentPanelMain, BoxLayout.PAGE_AXIS));
	
	JLabel childStartingLevelLabel = new JLabel("Child Starting Level:");
	childStartingLevelBox = new JComboBox();
		parentalUnitsPanel.add(childStartingLevelLabel);
		parentalUnitsPanel.add(childStartingLevelBox);

	//This panel has the name and class
	JPanel fixedParentPanel1 = new JPanel();
		fixedParentName = new JLabel("Name: ");
		fixedParentNameDisplay = new JLabel();
		fixedParentClass= new JLabel("Class: ");
		fixedParentClassDisplay = new JComboBox(jobs);
			fixedParentPanel1.add(fixedParentName);
			fixedParentPanel1.add(fixedParentNameDisplay);
			fixedParentPanel1.add(fixedParentClass);
			fixedParentPanel1.add(fixedParentClassDisplay);
			
	//this panel has the stats
		JPanel fixedParentPanel2 = new JPanel();
		fixedParentPanel2.setLayout(new GridLayout(4,4));
		
			fixedParentHP = new JLabel("HP: ");
			fixedParentHPField = new JTextField(" ", 4);
			fixedParentPanel2.add(fixedParentHP);
			fixedParentPanel2.add(fixedParentHPField);	
		
			fixedParentSpd = new JLabel("Spd: ");
			fixedParentSpdField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentSpd);
			fixedParentPanel2.add(fixedParentSpdField);		
				
			fixedParentStr = new JLabel("Str: ");
			fixedParentStrField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentStr);
			fixedParentPanel2.add(fixedParentStrField);	
				
			fixedParentLck = new JLabel("Lck: ");
			fixedParentLckField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentLck);
			fixedParentPanel2.add(fixedParentLckField);				
				
			fixedParentMag = new JLabel("Mag: ");
			fixedParentMagField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentMag);
			fixedParentPanel2.add(fixedParentMagField);
				
			fixedParentDef = new JLabel("Def: ");
			fixedParentDefField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentDef);
			fixedParentPanel2.add(fixedParentDefField);			
				
			fixedParentSkl = new JLabel("Skl: ");
			fixedParentSklField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentSkl);
			fixedParentPanel2.add(fixedParentSklField);	
				
			fixedParentRes = new JLabel("Res: ");
			fixedParentResField = new JTextField(" ");
			fixedParentPanel2.add(fixedParentRes);
			fixedParentPanel2.add(fixedParentResField);	
	
//These panels are for the varied parent
	JPanel variedParentPanelMain = new JPanel();
	Border variedParentPanelBorder = BorderFactory.createTitledBorder("Varied Parent");
	variedParentPanelMain.setBorder(variedParentPanelBorder);
	variedParentPanelMain.setLayout(new BoxLayout(variedParentPanelMain, BoxLayout.PAGE_AXIS));

	//This panel has the name and class
	JPanel variedParentPanel1 = new JPanel();
		variedParentName = new JLabel("Name: ");
		variedParentNameDisplay = new JComboBox();
			VariedParentBoxHandler variedparentboxhandler = new VariedParentBoxHandler();
			variedParentNameDisplay.addActionListener(variedparentboxhandler);
		variedParentClass= new JLabel("Class: ");
		variedParentClassDisplay = new JComboBox();
			variedParentPanel1.add(variedParentName);
			variedParentPanel1.add(variedParentNameDisplay);
			variedParentPanel1.add(variedParentClass);
			variedParentPanel1.add(variedParentClassDisplay);
	
			//this panel has the stats
		JPanel variedParentPanel2 = new JPanel();
		variedParentPanel2.setLayout(new GridLayout(4,4));
		
		variedParentHP = new JLabel("HP: ");
		variedParentHPField = new JTextField(" ", 4);
		variedParentPanel2.add(variedParentHP);
		variedParentPanel2.add(variedParentHPField);	
		
		variedParentSpd = new JLabel("Spd: ");
		variedParentSpdField = new JTextField(" ");
		variedParentPanel2.add(variedParentSpd);
		variedParentPanel2.add(variedParentSpdField);		
				
		variedParentStr = new JLabel("Str: ");
		variedParentStrField = new JTextField(" ");
		variedParentPanel2.add(variedParentStr);
		variedParentPanel2.add(variedParentStrField);	
				
		variedParentLck = new JLabel("Lck: ");
		variedParentLckField = new JTextField(" ");
		variedParentPanel2.add(variedParentLck);
		variedParentPanel2.add(variedParentLckField);				
				
		variedParentMag = new JLabel("Mag: ");
		variedParentMagField = new JTextField(" ");
		variedParentPanel2.add(variedParentMag);
		variedParentPanel2.add(variedParentMagField);
				
		variedParentDef = new JLabel("Def: ");
		variedParentDefField = new JTextField(" ");
		variedParentPanel2.add(variedParentDef);
		variedParentPanel2.add(variedParentDefField);			
				
		variedParentSkl = new JLabel("Skl: ");
		variedParentSklField = new JTextField(" ");
		variedParentPanel2.add(variedParentSkl);
		variedParentPanel2.add(variedParentSklField);	
				
		variedParentRes = new JLabel("Res: ");
		variedParentResField = new JTextField(" ");
		variedParentPanel2.add(variedParentRes);
		variedParentPanel2.add(variedParentResField);	

//This panel handles banes and boones
	JPanel baneboonPanel = new JPanel();
	baneboonPanel.setLayout(new GridLayout(2,1));
	boonLabel = new JLabel("Boon: ");
	boonBox = new JComboBox(new DefaultComboBoxModel(data.getBoons()));
		boonBox.setSelectedIndex(Arrays.asList(data.getBoons()).indexOf("Quick (Spd)"));
		boonBox.setEnabled(false);
	baneLabel = new JLabel("Bane: ");
	baneBox = new JComboBox(new DefaultComboBoxModel(data.getBanes()));
		baneBox.setSelectedIndex(Arrays.asList(data.getBanes()).indexOf("Unlucky (Lck)"));
		baneBox.setEnabled(false);
		baneboonPanel.add(boonLabel);
		baneboonPanel.add(boonBox);
		baneboonPanel.add(baneLabel);
		baneboonPanel.add(baneBox);

//This panel handles the buttons
	JPanel parentalButtonPanel = new JPanel();
	parentalConfirm = new JButton("Confirm");
		ParentalConfirmButtonHandler parentalconfirmbuttonhandler = new ParentalConfirmButtonHandler();
		parentalConfirm.addActionListener(parentalconfirmbuttonhandler);
			parentalButtonPanel.add(parentalConfirm);
	parentalCancel = new JButton("Cancel");
		ParentalCancelButtonHandler parentalcancelbuttonhandler = new ParentalCancelButtonHandler();
		parentalCancel.addActionListener(parentalcancelbuttonhandler);
			parentalButtonPanel.add(parentalCancel);

	//Adding all components
	fixedParentPanelMain.add(fixedParentPanel1);
	fixedParentPanelMain.add(fixedParentPanel2);
	variedParentPanelMain.add(variedParentPanel1);
	variedParentPanelMain.add(variedParentPanel2);
	parentalUnitsPanel.add(fixedParentPanelMain);
	parentalUnitsPanel.add(variedParentPanelMain);
	parentalUnitsPanel.add(baneboonPanel);
	parentalUnitsPanel.add(parentalButtonPanel);

	//Finalizing Panel
	parentalUnitsPane.add(parentalUnitsPanel);
	parentalUnitsPane.setTitle("Parents");
	parentalUnitsPane.setResizable(true);
	parentalUnitsPane.setSize(375,450);
	parentalUnitsPane.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

	//Add all Components
	optionPane.setLayout(new FlowLayout());
	optionPane.add(listPanel);
	optionPane.add(modPanel);
	
	//Window
	optionPane.setTitle("Character Options");
	optionPane.setResizable(true);
	optionPane.setSize(600,250);
	optionPane.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //not sure if this is right, will check when testing
	
	//SETS DEFAULT UNIT TO SILAS... FOR DEBUGGING FOR NOW...
	inputCharBox.setSelectedIndex(Arrays.asList(conquestCharacters).indexOf("Silas"));

	//THIS IS WORK IN PROGRESS FOR THE GRAPH
	graphcontroller.createGraph("");
	ChartPanel inputChart = graphcontroller.getChartPanel();
	graphPanel2.add(inputChart);
	
	this.pack();
}
//=============================================================END GUI CLASS======================================================

//------------------------------------------------------------ACTION LISTENERS-----------------------------------------------------
	
//-------------------------------------------------------------OPTION WINDOW-------------------------------------------------------
	//This handles the button that opens the option window from the main window
	public class OpenOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			optionPane.setVisible(true);
		}
		
	}
	//This handles the button that opens the reclass window from the options window
	public class ReclassOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			UnitController unitcontroller = UnitController.getInstance();
			
			String newJob = reclassBox.getSelectedItem().toString();

			int baseLevel = calculateBaseLevel();
			
			// the level we want to reclass at is equal to the index selected in the jobHistory + whatever our base level is
			int newLevel = jobHistory.getSelectedIndex() + baseLevel;
			System.out.println("newLevel: " + newLevel + ", baseLevel: " + baseLevel);
			
			unitcontroller.reclass(newJob, newLevel, baseLevel);

			Object[] listData = unitcontroller.getClassArray(baseLevel);
			jobHistory.setListData(listData);
		}
		
	}
	//This handles the promote button in the options window (NOT IMPLEMENTED YET)
	public class PromoteOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		}
		
	}
	////This handles the Eternal Seal button in the options window (NOT IMPLEMENTED YET)
	public class EternalSealButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		}
		
	}
	//This handles the parents button in the options window (ALMOST IMPLEMENTED)
	public class ParentalUnitsButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			parentalUnitsPane.setVisible(true);
		}
		
	}
		//This handles the child starting level box in
			public class childStartingLevelHandler implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					inputLevelBox.setSelectedIndex(childStartingLevelBox.getSelectedIndex());
				}			
			}
		//This handles the confirm button in the parental units window
			public class ParentalConfirmButtonHandler implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
					DataStorage data = DataStorage.getInstance();		
					UnitController unitcontroller = UnitController.getInstance();
					//Storing the ChildCharacter, Job, Route, and BaseLevel
					try
					{
						domain.ChildCharacter tempChildChar;
						domain.Job tempJob;
						String tempRoute;
						int tempStartLevel = Integer.parseInt(childStartingLevelBox.getSelectedItem().toString());
						ArrayList<String> tempClassHistory;
						
						System.out.println("unitcontroller startLevel: " + unitcontroller.getStartLevel() + ", tempStartLevel: " + tempStartLevel);
						
						// If the current character in unitcontroller is a child && it's the same character as what we selected 
						// && the character hasn't changed their base level && the result field is not empty (aka it's not the first time creating data)
						// Then the user is accessing the parental unit pane to change some data. Don't change the character, their base job, their route, or their class history
						// by taking the data already in unitcontroller
						if(unitcontroller.currentChar.getIsChild() && unitcontroller.getCurrentChar().getName().equals(inputCharBox.getSelectedItem().toString()) 
								&& unitcontroller.getStartLevel() == Integer.parseInt(childStartingLevelBox.getSelectedItem().toString()) && !resultHPField.getText().equals("")) {
							tempChildChar = (domain.ChildCharacter) unitcontroller.getCurrentChar();
							tempJob = unitcontroller.getCurrentJob();
							tempRoute = unitcontroller.getCurrentRoute();
							tempClassHistory = unitcontroller.getClassHistory();
						}
						// Otherwise make a new unit by taking the data from the input
						else {
							tempChildChar = (domain.ChildCharacter) data.getCharacters().get(inputCharBox.getSelectedItem().toString());
					 		tempJob = data.getJobs().get(tempChildChar.getBaseClass());
							tempRoute = inputRouteBox.getSelectedItem().toString();
							
							//Making the class history
							tempClassHistory = new ArrayList<String>();						
							for(int i = tempStartLevel; i<=tempJob.getMaxStats(0); i++)
							{
								tempClassHistory.add(tempJob.getName());
							}
						}
						
						domain.Unit tempFixedParent = new domain.Unit((domain.Character) data.getCharacters().get(fixedParentNameDisplay.getText()), 
																		(domain.Job) data.getJobs().get(fixedParentClassDisplay.getSelectedItem().toString()), 
																		inputRouteBox.getSelectedItem().toString());
						
						domain.Unit tempVariedParent = new domain.Unit((domain.Character) data.getCharacters().get(variedParentNameDisplay.getSelectedItem().toString()),
																		(domain.Job) data.getJobs().get(variedParentClassDisplay.getSelectedItem().toString()),
																		inputRouteBox.getSelectedItem().toString());
						
						double[] tempFixedParentStats = new double[] {Double.parseDouble(fixedParentHPField.getText()),
																		Double.parseDouble(fixedParentStrField.getText()), 
																		Double.parseDouble(fixedParentMagField.getText()), 
																		Double.parseDouble(fixedParentSklField.getText()), 
																		Double.parseDouble(fixedParentSpdField.getText()), 
																		Double.parseDouble(fixedParentLckField.getText()), 
																		Double.parseDouble(fixedParentDefField.getText()), 
																		Double.parseDouble(fixedParentResField.getText())};
						
						double[] tempVariedParentStats = new double[] {Double.parseDouble(variedParentHPField.getText()), 
																		Double.parseDouble(variedParentStrField.getText()), 
																		Double.parseDouble(variedParentMagField.getText()), 
																		Double.parseDouble(variedParentSklField.getText()), 
																		Double.parseDouble(variedParentSpdField.getText()), 
																		Double.parseDouble(variedParentLckField.getText()), 
																		Double.parseDouble(variedParentDefField.getText()), 
																		Double.parseDouble(variedParentResField.getText())};				
						
						/*
						// PRETTY SURE THIS IS POINTLESS T.T
						// Run a check to see if the user is changing the start level of the unit to a reclassed level 
						// For example, say I do Nyx!Sophie and start her at level 15. Then I reclass her to Dark Mage at level 17.
						// Next, I want to change her base level to level 17. She will still retain her reclassing as a Dark Mage.
						// First, we need to make sure that the character we're working with is a child (since unitcontroller is never cleared)
						if(unitcontroller.getCurrentChar().getIsChild()) {
							// Search through the entire job history
							for(int i = 0; i < jobHistory.getModel().getSize(); i++) {
								// Get the string of the job history at each index
								String jobHistoryItemString = (String) jobHistory.getModel().getElementAt(i);
								// Parse it to only find be the level
								// This replaces all non-integers in a string with "" and then parses it to an int
								int jobHistoryLevel = Integer.parseInt(jobHistoryItemString.replaceAll("[\\D]", ""));
								// Also parse it to only be the name of the job
								// The reason for adding 2 to the index of "." is to exclude the space before it as well
								String jobNameSubstring = jobHistoryItemString.substring(jobHistoryItemString.indexOf(".") + 2, jobHistoryItemString.length());
								//System.out.println("DAS JOB " + jobNameSubstring);
								// If the level we want to start at matches a level in our job history
								// And the name of the job in the job history is not the same as the child's base job,
								// Then the job we use is now the job in the job history rather then the base job
								// Break the loop since we found what we came for
								if(tempStartLevel == jobHistoryLevel && !data.getJobs().get(tempChildChar.getBaseClass()).getName().equals(jobNameSubstring))
								{
									tempJob = data.getJobs().get(jobNameSubstring);
									break;
								}
							}
						}*/
						
						//Update UnitController for a child
						unitcontroller.setCurrentChar(tempChildChar);
						unitcontroller.setCurrentJob(tempJob);
						unitcontroller.setCurrentRoute(tempRoute);
						unitcontroller.setClassHistory(tempClassHistory);
						unitcontroller.setFixedParent(tempFixedParent);
						unitcontroller.setVariedParent(tempVariedParent);
						unitcontroller.setFixedParentInputStats(tempFixedParentStats);
						unitcontroller.setVariedParentInputStats(tempVariedParentStats);
						unitcontroller.setStartLevel(tempStartLevel);
						
						// Make a temporary child unit to get the base stat data
						domain.Unit tempChildUnit = new domain.Unit(tempChildChar, tempJob, tempRoute, tempFixedParentStats, tempFixedParent, tempVariedParentStats, tempVariedParent, tempStartLevel);
						
						//Sets the stat boxes
						inputHPField.setText(""+(int)tempChildUnit.getBaseStats()[0]);
						inputStrField.setText(""+(int)tempChildUnit.getBaseStats()[1]);
						inputMagField.setText(""+(int)tempChildUnit.getBaseStats()[2]);
						inputSklField.setText(""+(int)tempChildUnit.getBaseStats()[3]);
						inputSpdField.setText(""+(int)tempChildUnit.getBaseStats()[4]);
						inputLckField.setText(""+(int)tempChildUnit.getBaseStats()[5]);
						inputDefField.setText(""+(int)tempChildUnit.getBaseStats()[6]);
						inputResField.setText(""+(int)tempChildUnit.getBaseStats()[7]);
						
						//System.out.println(tempLevel);
						
						Object[] listData = unitcontroller.getClassArray(tempStartLevel);
						jobHistory.setListData(listData);
						jobHistory.setSelectedIndex(0);
				
						inputLevelBox.setModel(childStartingLevelBox.getModel());
						
						//setting up child level box
						String[] possibleLevels = new String[(tempJob.getMaxStats(0) - tempStartLevel + 1)];
						for(int i = 0; i<=(tempJob.getMaxStats(0) - tempStartLevel); i++)
						{
							possibleLevels[i] = (i+tempStartLevel+"");
						}
						
						resultLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
						// display the class of whatever class we set above in case of changing levels after reclassing
						resultClassDisplay.setText("Lvl. "+tempStartLevel+" "+tempJob.getName());
						
						//resultLevelBox.setSelectedIndex(childStartingLevelBox.getSelectedIndex());
						resultLevelBox.setEnabled(true);
						
						//Debug print to console
						System.out.println("Character: "+unitcontroller.getCurrentChar().getName());
						System.out.println("Base Class: "+unitcontroller.getCurrentJob().getName());
						System.out.println("Base Level: "+ unitcontroller.getCurrentChar().getBaseStats().getStats(unitcontroller.getCurrentRoute(),0));
						System.out.println("Route: "+unitcontroller.getCurrentRoute());
						for(int i = 0; i<tempClassHistory.size();i++)
						{
							System.out.println(tempClassHistory.get(i));
						}
					}
					catch(NullPointerException exception) //TEMPORARY UNTIL ALL CHARACTERS ARE IMPLEMENTED
					{
						JOptionPane.showMessageDialog(GUI.this, "Sorry, this character is not implemented yet!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					parentalUnitsPane.dispose();
				}
			}
		//This handles the close button in the parental units window
			public class ParentalCancelButtonHandler implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					parentalUnitsPane.dispose();
				}
			}
		// This handles creating the list of jobs based on the variedParent chosen in the parental units window
			public class VariedParentBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(parentalUnitsPane.isVisible())
			{
				DataStorage data = DataStorage.getInstance();
				domain.Character tempVariedParent = data.getCharacters().get(variedParentNameDisplay.getSelectedItem().toString());
				domain.Job tempVariedParentJob = data.getJobs().get(tempVariedParent.getBaseClass());
				String tempRoute = inputRouteBox.getSelectedItem().toString();
				
				// Set up jobs list
				variedParentClassDisplay.setModel(new DefaultComboBoxModel(jobs));
				variedParentClassDisplay.setSelectedIndex(Arrays.asList(jobs).indexOf(tempVariedParentJob.getName()));
				
				// Set fields with base stats
				variedParentHPField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 1));
				variedParentStrField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 2));
				variedParentMagField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 3));
				variedParentSklField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 4));
				variedParentSpdField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 5));
				variedParentLckField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 6));
				variedParentDefField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 7));
				variedParentResField.setText(""+tempVariedParent.getBaseStats().getStats(tempRoute, 8));
			}
		}
	}
	//This handles the button that closes the options window
	public class CloseOptionButtonHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		UnitController unitcontroller = UnitController.getInstance();
		int resultLevel = Integer.parseInt(resultLevelBox.getSelectedItem().toString()); 
		int baseLevel = calculateBaseLevel();
		resultClassDisplay.setText(jobHistory.getModel().getElementAt(resultLevel-baseLevel).toString());
		optionPane.dispose();
	}
	
}

//-------------------------------------------------------------MAIN WINDOW-------------------------------------------------------
	//This clears all data
	public class ClearButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			GraphController graphcontroller = GraphController.getInstance();
			
			//inputCharBox.setSelectedIndex(1);			
				clearResults();
				
				variedParentHPField.setText("");
				variedParentStrField.setText("");
				variedParentMagField.setText("");
				variedParentSklField.setText("");
				variedParentSpdField.setText("");
				variedParentLckField.setText("");
				variedParentDefField.setText("");
				variedParentResField.setText("");
				
				variedParentNameDisplay.setSelectedIndex(-1);
				variedParentClassDisplay.removeAllItems();
				
				graphcontroller.setDataset(graphcontroller.createDataset());
				inputLevelBox.setSelectedIndex(0);
				resultLevelBox.setEnabled(false);
				graphStatBox.setEnabled(false);
		}	
	}
	//This generates the unitSheet and populates the result fields and graph
	public class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{			
			int HP = 0;
			int Str = 0;
			int Mag = 0;
			int Skl = 0;
			int Spd = 0;
			int Lck = 0;
			int Def = 0;
			int Res = 0;
			
			UnitController unitcontroller = UnitController.getInstance();
			GraphController graphcontroller = GraphController.getInstance();
			
			int inputLevel; 	// User input
			int baseLevel;  	// Starting level of unit
			int inputJobIndex;	// index of the job in the classhistory
			
			// If the Character in UnitController isn't the same as the one they want when they click calculate, 
			// it's because the user tried to select a child without setting up parents. So give the user a message.
			// Also check the specific case that the user cleared after working with a child character as that should reset things, but does not change the selected character in the index
			// To do so, check if the unitcontroller currentChar is a child, if the name in the input box is the same, and if variedParentNameDisplay selected index is set to the default -1
			if(!unitcontroller.getCurrentChar().getName().equals(inputCharBox.getSelectedItem().toString()) || 
					(unitcontroller.getCurrentChar().getIsChild() && unitcontroller.getCurrentChar().getName().equals(inputCharBox.getSelectedItem().toString())
							&& variedParentNameDisplay.getSelectedIndex() == -1)) {
				JOptionPane.showMessageDialog(GUI.this, "Please set up Parents in Child Options");
			}
			else {
				try
				{
					HP = Integer.parseInt(inputHPField.getText());
					Str = Integer.parseInt(inputStrField.getText());
					Mag = Integer.parseInt(inputMagField.getText());
					Skl = Integer.parseInt(inputSklField.getText());
					Spd = Integer.parseInt(inputSpdField.getText());
					Lck = Integer.parseInt(inputLckField.getText());
					Def = Integer.parseInt(inputDefField.getText());
					Res = Integer.parseInt(inputResField.getText());
				}
				catch(NumberFormatException f)
				{
					JOptionPane.showMessageDialog(GUI.this, "Please enter a number for the stats", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
	// WHY IS THIS GIVING THE WRONG VALUE?!
				// if the character being checked is a child, then the input level is from the level box in the child options (because the inputLevelBox is hidden)
				// inputJobIndex is always 0 because technically the inputed level for a child character is always the base level (aka index 0)
				if(unitcontroller.getCurrentChar().getIsChild())
				{
					inputLevel = Integer.parseInt(childStartingLevelBox.getSelectedItem().toString());
					inputJobIndex = 0;
				}
				// otherwise the inputLevel is equal to the level selected in the inputLevelBox
				// otherwise the inputJobIndex is of the index of the inputLevelBox
				else
				{
					inputLevel = Integer.parseInt(inputLevelBox.getSelectedItem().toString());
					inputJobIndex = inputLevelBox.getSelectedIndex();
				}
				baseLevel = calculateBaseLevel();
				//inputJobIndex = jobHistory.getSelectedIndex();
				double[]inputStats = {HP, Str, Mag, Skl, Spd, Lck, Def,Res};
				
				unitcontroller.buildInputUnitSheet(inputLevel, inputStats, inputJobIndex);
				unitcontroller.buildLocalUnitSheet(inputJobIndex);
				
			//UPDATES GRAPH
				int stat = graphStatBox.getSelectedIndex();
	
				double[] LocalStatSpread = unitcontroller.getLocalStatSpread(stat);
				double[] InputStatSpread = unitcontroller.getInputStatSpread(stat);
			
			System.out.println("INPUT LEVEL" + inputLevel);
			System.out.println("GRAPH IS DISPLAYING:" +(inputLevel)+" - "+(baseLevel));
				graphcontroller.setDataset(graphcontroller.createDataset(LocalStatSpread, InputStatSpread, inputLevel, baseLevel));
	
				graphStatBox.setEnabled(true);
				
			//UPDATES BOXES			
				int resultLevel = Integer.parseInt(resultLevelBox.getSelectedItem().toString()); 
	
				double[] inputResults = unitcontroller.getInputUnitSheet().get(resultLevelBox.getSelectedIndex()).getBaseStats();
				double[] localResults = unitcontroller.getLocalUnitSheet().get(resultLevel-baseLevel).getBaseStats();
				
				DecimalFormat formatter = new DecimalFormat( "##.##" );
				resultLevelBox.setEnabled(true);
				
				resultHPField.setText(formatter.format(inputResults[0]));
				avgHPField.setText(formatter.format(localResults[0]));
				resultHPDifference.setText(formatter.format(inputResults[0] - localResults[0]));
					if((inputResults[0] - localResults[0]) < 0)
					{
						resultHPDifference.setBackground(Color.RED);
					}
					else if ((inputResults[0] - localResults[0]) > 0)
					{
						resultHPDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultHPDifference.setBackground(Color.WHITE);
					}
				resultStrField.setText(formatter.format(inputResults[1]));
				avgStrField.setText(formatter.format(localResults[1]));
				resultStrDifference.setText(formatter.format(inputResults[1] - localResults[1]));
					if((inputResults[1] - localResults[1]) < 0)
					{
						resultStrDifference.setBackground(Color.RED);
					}
					else if ((inputResults[1] - localResults[1]) > 0)
					{
						resultStrDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultStrDifference.setBackground(Color.WHITE);
					}
				resultMagField.setText(formatter.format(inputResults[2]));
				avgMagField.setText(formatter.format(localResults[2]));
				resultMagDifference.setText(formatter.format(inputResults[2] - localResults[2]));
					if((inputResults[2] - localResults[2]) < 0)
					{
						resultMagDifference.setBackground(Color.RED);
					}
					else if ((inputResults[2] - localResults[2]) > 0)
					{
						resultMagDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultMagDifference.setBackground(Color.WHITE);
					}
				resultSklField.setText(formatter.format(inputResults[3]));
				avgSklField.setText(formatter.format(localResults[3]));
				resultSklDifference.setText(formatter.format(inputResults[3] - localResults[3]));
					if((inputResults[3] - localResults[3]) < 0)
					{
						resultSklDifference.setBackground(Color.RED);
					}
					else if ((inputResults[3] - localResults[3]) > 0)
					{
						resultSklDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultSklDifference.setBackground(Color.WHITE);
					}
				resultSpdField.setText(formatter.format(inputResults[4]));
				avgSpdField.setText(formatter.format(localResults[4]));
				resultSpdDifference.setText(formatter.format(inputResults[4] - localResults[4]));
					if((inputResults[4] - localResults[4]) < 0)
					{
						resultSpdDifference.setBackground(Color.RED);
					}
					else if ((inputResults[4] - localResults[4]) > 0)
					{
						resultSpdDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultSpdDifference.setBackground(Color.WHITE);
					}
				resultLckField.setText(formatter.format(inputResults[5]));
				avgLckField.setText(formatter.format(localResults[5]));
				resultLckDifference.setText(formatter.format(inputResults[5] - localResults[5]));
					if((inputResults[5] - localResults[5]) < 0)
					{
					resultLckDifference.setBackground(Color.RED);
					}
					else if ((inputResults[5] - localResults[5]) > 0)
					{
						resultLckDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultLckDifference.setBackground(Color.WHITE);
					}
				resultDefField.setText(formatter.format(inputResults[6]));
				avgDefField.setText(formatter.format(localResults[6]));
				resultDefDifference.setText(formatter.format(inputResults[6] - localResults[6]));
					if((inputResults[6] - localResults[6]) < 0)
					{
						resultDefDifference.setBackground(Color.RED);
					}
					else if ((inputResults[6] - localResults[6]) > 0)
					{
						resultDefDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultDefDifference.setBackground(Color.WHITE);
					}
				resultResField.setText(formatter.format(inputResults[7]));
				avgResField.setText(formatter.format(localResults[7]));
				resultResDifference.setText(formatter.format(inputResults[7] - localResults[7]));		
					if((inputResults[7] - localResults[7]) < 0)
					{
						resultResDifference.setBackground(Color.RED);
					}
					else if ((inputResults[7] - localResults[7]) > 0)
					{
						resultResDifference.setBackground(Color.GREEN);
					}
					else 
					{
						resultResDifference.setBackground(Color.WHITE);
					}
				//Stuff for Debug
				//unitcontroller.printLocalSheet();
				//unitcontroller.printInputSheet();
			}
		}	
	}
	//This allows you to change the data visualized on the graph using the stat combo box
	public class graphBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			UnitController unitcontroller = UnitController.getInstance();
			GraphController graphcontroller = GraphController.getInstance();
			
			int stat = graphStatBox.getSelectedIndex();
			int startLevel = Integer.parseInt(inputLevelBox.getSelectedItem().toString());
			int baseLevel = calculateBaseLevel();

			double[] LocalStatSpread = unitcontroller.getLocalStatSpread(stat);
			double[] InputStatSpread = unitcontroller.getInputStatSpread(stat);;
			graphcontroller.setDataset(graphcontroller.createDataset(LocalStatSpread, InputStatSpread, startLevel,baseLevel));
			System.out.println("INPUT LEVEL" + startLevel);
			System.out.println("GRAPH IS DISPLAYING:" +(startLevel)+" - "+(baseLevel));

		}
	}
	//This handler allows a user to select a route and adjusts data in the logic based on route.
	public class ComboBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(inputRouteBox.getSelectedItem() == "Conquest")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(conquestCharacters));
			    inputCharBox.setSelectedIndex(Arrays.asList(conquestCharacters).indexOf("Silas"));
			    repaint();
			}
			else if(inputRouteBox.getSelectedItem() == "Birthright")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(birthrightCharacters));
			    inputCharBox.setSelectedIndex(Arrays.asList(birthrightCharacters).indexOf("Silas"));
			    repaint();
			}
			if(inputRouteBox.getSelectedItem() == "Revelations")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(revelationsCharacters));
			    inputCharBox.setSelectedIndex(Arrays.asList(revelationsCharacters).indexOf("Silas"));
			    repaint();
			}
		}
	}
	//This handler allows a user to select the character and adjusts data accordingly
	public class CharBoxHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		DataStorage data = DataStorage.getInstance();		
		UnitController unitcontroller = UnitController.getInstance();

		//Storing the Character, Job, Route, and BaseLevel
		try
		{
			clearResults();
			domain.Character tempChar = data.getCharacters().get(inputCharBox.getSelectedItem().toString());
	 		domain.Job tempJob = data.getJobs().get(tempChar.getBaseClass());
			String tempRoute = inputRouteBox.getSelectedItem().toString();
			int tempLevel = tempChar.getBaseStats().getStats(tempRoute, 0);
			
			//Checks for children and adjusts GUI as necessary
			if(tempChar.getIsChild() == true)
			{
				inputLevel.setVisible(false);
				inputLevelBox.setVisible(false);
				parentalUnitsButton.setVisible(true);
				
				JOptionPane.showMessageDialog(GUI.this, "Please input parents' stats");
				
				// temp variable for the child
				domain.ChildCharacter tempChildChar = (domain.ChildCharacter)data.getCharacters().get(inputCharBox.getSelectedItem().toString());
				
				// Set up the default fixedParent
				// temp variable for the fixedParent
				domain.Character tempFixedParent = data.getCharacters().get(tempChildChar.getFixedParent());
				// Set the name and class (among the list of classes)
				fixedParentNameDisplay.setText(tempChildChar.getFixedParent());
				fixedParentClassDisplay.setSelectedIndex(Arrays.asList(jobs).indexOf(tempFixedParent.getBaseClass()));
				
				// Set fields with base stats
				fixedParentHPField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 1));
				fixedParentStrField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 2));
				fixedParentMagField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 3));
				fixedParentSklField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 4));
				fixedParentSpdField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 5));
				fixedParentLckField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 6));
				fixedParentDefField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 7));
				fixedParentResField.setText(""+tempFixedParent.getBaseStats().getStats(tempRoute, 8));
				
				// Set up variedParents
				variedParentNameDisplay.setModel(new DefaultComboBoxModel(tempChildChar.getVariedParents().getVariedParentsList(tempRoute)));
				variedParentNameDisplay.setSelectedIndex(-1);
				variedParentClassDisplay.removeAllItems();
				
				//setting up child level box
				String[] possibleLevels = new String[(tempJob.getMaxStats(0) - tempLevel + 1)];	
				for(int i = 0; i<=(tempJob.getMaxStats(0) - tempLevel); i++)
				{
					possibleLevels[i] = (i+tempLevel+"");
				}
				childStartingLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
				
				parentalUnitsPane.setVisible(true);
				parentalUnitsPane.setEnabled(true);
			}
			else
			{
				parentalUnitsButton.setVisible(false);
				inputLevel.setVisible(true);
				inputLevelBox.setVisible(true);
				
				//Making the class history
				ArrayList<String> tempClassHistory = new ArrayList();
				int levelMod = 0;
				for(int i = tempLevel; i<=tempJob.getMaxStats(0); i++)
				{
					String input = tempChar.getBaseClass();
					tempClassHistory.add(input);
					levelMod++;
				}
						
				//Update UnitController
				unitcontroller.setCurrentChar(tempChar);
				unitcontroller.setCurrentJob(tempJob);
				unitcontroller.setCurrentRoute(tempRoute);
				unitcontroller.setClassHistory(tempClassHistory);
				
				//Sets the stat boxes
				inputHPField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 1));
				inputStrField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 2));
				inputMagField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 3));
				inputSklField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 4));
				inputSpdField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 5));
				inputLckField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 6));
				inputDefField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 7));
				inputResField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 8));
				
				//This code will set the level fields and possible classes in the character option windows
				String[] possibleLevels = new String[(tempJob.getMaxStats(0) - tempLevel + 1)];	
				for(int i = 0; i<=(tempJob.getMaxStats(0) - tempLevel); i++)
				{
					possibleLevels[i] = (i+tempLevel+"");
				}
				
				//System.out.println(tempLevel);

				Object[] listData = unitcontroller.getClassArray(unitcontroller.getCurrentChar().getBaseStats().getStats(unitcontroller.getCurrentRoute(), 0));
				jobHistory.setListData(listData);
				jobHistory.setSelectedIndex(0);

				inputLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
				resultLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
				resultClassDisplay.setText("Lvl. "+tempChar.getBaseStats().getStats(tempRoute, 0)+" "+tempChar.getBaseClass());
			
				//Debug print to console
				System.out.println("Character: "+unitcontroller.getCurrentChar().getName());
				System.out.println("Base Class: "+unitcontroller.getCurrentJob().getName());
				System.out.println("Base Level: "+ unitcontroller.getCurrentChar().getBaseStats().getStats(unitcontroller.getCurrentRoute(),0));
				System.out.println("Route: "+unitcontroller.getCurrentRoute());
				for(int i = 0; i<tempClassHistory.size();i++)
				{
					System.out.println(tempClassHistory.get(i));
				}	
			}
		}
		catch(NullPointerException exception) //TEMPORARY UNTIL ALL CHARACTERS ARE IMPLEMENTED
		{
			JOptionPane.showMessageDialog(GUI.this, "Sorry, this character is not implemented yet!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
	//This handler populates the resultLevel box based on whats in the level box
	public class InputLevelBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DataStorage data = DataStorage.getInstance();
			UnitController unitcontroller = UnitController.getInstance();
			
			int inputLevel = Integer.parseInt(inputLevelBox.getSelectedItem().toString()); 
			int baseLevel = calculateBaseLevel();
				
			resultClassDisplay.setText(jobHistory.getModel().getElementAt(inputLevel-baseLevel).toString());
			
			Object[] possibleLevels = new Object[(unitcontroller.getClassHistory().size()) - (inputLevel - baseLevel)];
			
			for(int i = 0; i< (possibleLevels.length); i++)
			{
					possibleLevels[i] = inputLevel;
					inputLevel++;
					//System.out.println(i);
			}
			resultLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
		}
	}
	//This handler updates the result panel based on the input level
	public class ResultLevelBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UnitController unitcontroller = UnitController.getInstance();
			
			int resultLevel = Integer.parseInt(resultLevelBox.getSelectedItem().toString()); 
			int baseLevel = calculateBaseLevel();

			double[] inputResults = unitcontroller.getInputUnitSheet().get(resultLevelBox.getSelectedIndex()).getBaseStats();
			double[] localResults = unitcontroller.getLocalUnitSheet().get(resultLevel-baseLevel).getBaseStats();
			
			DecimalFormat formatter = new DecimalFormat( "##.##" );
			resultClassDisplay.setText(jobHistory.getModel().getElementAt(resultLevel-baseLevel).toString());

			resultHPField.setText(formatter.format(inputResults[0]));
			avgHPField.setText(formatter.format(localResults[0]));
			resultHPDifference.setText(formatter.format(inputResults[0] - localResults[0]));
			
			resultStrField.setText(formatter.format(inputResults[1]));
			avgStrField.setText(formatter.format(localResults[1]));
			resultStrDifference.setText(formatter.format(inputResults[1] - localResults[1]));

			resultMagField.setText(formatter.format(inputResults[2]));
			avgMagField.setText(formatter.format(localResults[2]));
			resultMagDifference.setText(formatter.format(inputResults[2] - localResults[2]));

			resultSklField.setText(formatter.format(inputResults[3]));
			avgSklField.setText(formatter.format(localResults[3]));
			resultSklDifference.setText(formatter.format(inputResults[3] - localResults[3]));
			
			resultSpdField.setText(formatter.format(inputResults[4]));
			avgSpdField.setText(formatter.format(localResults[4]));
			resultSpdDifference.setText(formatter.format(inputResults[4] - localResults[4]));
			
			resultLckField.setText(formatter.format(inputResults[5]));
			avgLckField.setText(formatter.format(localResults[5]));
			resultLckDifference.setText(formatter.format(inputResults[5] - localResults[5]));
			
			resultDefField.setText(formatter.format(inputResults[6]));
			avgDefField.setText(formatter.format(localResults[6]));
			resultDefDifference.setText(formatter.format(inputResults[6] - localResults[6]));

			resultResField.setText(formatter.format(inputResults[7]));
			avgResField.setText(formatter.format(localResults[7]));
			resultResDifference.setText(formatter.format(inputResults[7] - localResults[7]));			
		}
	}
	// This is a method that clears the results field
	public void clearResults() {
		resultHPField.setText("");
		resultStrField.setText("");
		resultMagField.setText("");
		resultSpdField.setText("");
		resultSklField.setText("");
		resultLckField.setText("");
		resultDefField.setText("");
		resultResField.setText("");
		
		avgHPField.setText("");
		avgStrField.setText("");
		avgMagField.setText("");
		avgSklField.setText("");
		avgSpdField.setText("");
		avgLckField.setText("");
		avgDefField.setText("");
		avgResField.setText("");
		
		resultHPDifference.setText("");
		resultHPDifference.setBackground(Color.WHITE);
		resultStrDifference.setText("");
		resultStrDifference.setBackground(Color.WHITE);
		resultMagDifference.setText("");
		resultMagDifference.setBackground(Color.WHITE);
		resultSklDifference.setText("");
		resultSklDifference.setBackground(Color.WHITE);
		resultSpdDifference.setText("");
		resultSpdDifference.setBackground(Color.WHITE);
		resultLckDifference.setText("");
		resultLckDifference.setBackground(Color.WHITE);
		resultDefDifference.setText("");
		resultDefDifference.setBackground(Color.WHITE);
		resultResDifference.setText("");
		resultResDifference.setBackground(Color.WHITE);
	}
	// Determines the baseLevel of a character which is different if the Character chosen is a child character or no
	public int calculateBaseLevel() {
		UnitController unitcontroller = UnitController.getInstance();
		int baseLevel;
		// if the character being checked is a child, then the base level what the user inputed in child options
		if(unitcontroller.getCurrentChar().getIsChild()) {
			baseLevel = Integer.parseInt(childStartingLevelBox.getSelectedItem().toString());
		}
		// otherwise the base level is the character's natural base level
		else {
			baseLevel = unitcontroller.getCurrentChar().getBaseStats().getStats(unitcontroller.getCurrentRoute(), 0); 
		}
		return baseLevel;
	}
}
