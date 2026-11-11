package com.example.data

import com.example.model.AlignmentExercise
import com.example.model.Principle
import com.example.model.QuizQuestion
import com.example.model.SectionContent
import com.example.model.StudyChapter

object StudyGuideData {

    val bookOverview = """
        "The Law Behind The Laws" (by Owen Hermstring) moves past pop-psychology and superficial 'positive thinking' clichés to reveal the unchanging, universal constants that dictate human experience. 

        Just as gravity operates unyieldingly whether you believe in it or not, universal energetic laws govern the relationship between consciousness, perception, subconscious conditioning, and physical reality.

        True manifestation is not desperate hustling or wishing upon a star—it is intentional alignment, energetic restructuring, and the systematic dismantling of subconscious resistance.
    """.trimIndent()

    val universalPrinciples = listOf(
        Principle(
            id = "principle_1",
            title = "The Law of Primary Cause (Consciousness)",
            subtitle = "Consciousness precedes matter and form",
            coreAxiom = "Reality does not happen TO you; reality mirrors the sum frequency of what you accept as true.",
            deepExplanation = "Most people attempt to manipulate physical effects (working 80 hours a week, forcing relationships, obsessing over bank balances) while leaving the primary cause (their baseline internal state) unchanged. The primary law dictates that consciousness is the projector; the external world is merely the screen. Attempting to wipe smudge off the screen without cleaning the lens of perception is the ultimate human futility.",
            scientificSpiritualAnalogy = "Quantum collapse: Until an observer interacts with a wave function, potential remains in superposition. Your baseline awareness is the ongoing measurement that collapses potentiality into tangible events.",
            practicalApplication = "Before taking physical action toward any goal, sit in stillness for 3 minutes until you inhabit the psychological sensation of the outcome already being settled.",
            journalPrompt = "Where in my life am I furiously scrubbing the projector screen (trying to force outer circumstances) instead of changing what is inside the projector?",
            dailyAffirmation = "I operate as the cause, not the victim of effects. My internal state governs my external reality."
        ),
        Principle(
            id = "principle_2",
            title = "The Law of Resonance & Vibration",
            subtitle = "Like frequencies inevitably intersect",
            coreAxiom = "You never attract what you want; you attract what you are.",
            deepExplanation = "Wanting implies lacking. If your internal state vibrates with 'I need money because I am broke,' the dominant frequency broadcast is scarcity and desperation. The universe responds with mathematical precision to that frequency, delivering more scenarios to justify your sense of lack. You must cultivate the energetic resonance of fulfillment, gratitude, and security before physical proof arrives.",
            scientificSpiritualAnalogy = "Sympathetic resonance: When two tuning forks share the exact same pitch, striking one fork will cause the other across the room to vibrate without any physical touch.",
            practicalApplication = "Identify 3 things you believe you will feel when your goal is achieved (e.g., relief, freedom, delight). Find ways to evoke those exact three emotions today in small, unconditional ways.",
            journalPrompt = "If my thoughts and emotions over the past 48 hours were broadcast as radio waves, what station would I be transmitting?",
            dailyAffirmation = "I dwell in the energetic frequency of sufficiency, abundance, and profound inner peace."
        ),
        Principle(
            id = "principle_3",
            title = "The Law of Perception Architecture",
            subtitle = "Perception filters determine accessible pathways",
            coreAxiom = "You do not see the world as it is; you see the world as you have conditioned your filters to interpret it.",
            deepExplanation = "Your subconscious mind processes millions of bits of sensory input per second, but your Reticular Activating System (RAS) only surfaces data congruent with your core assumptions. If you subconsciously believe 'people always take advantage of me,' your brain highlights cues of betrayal and ignores cues of genuine loyalty. Changing your perception architecture literally reconstructs the world you experience.",
            scientificSpiritualAnalogy = "Optical polarization: Polarized lenses block specific light wavelengths while allowing others through. Your beliefs are polarized filters on quantum potential.",
            practicalApplication = "Notice when you make a sweeping negative assumption (e.g., 'Nobody respects my time'). Immediately formulate two alternate interpretations that are equally plausible.",
            journalPrompt = "What lifelong assumption about myself or others have I treated as absolute fact, when it was simply a conditioned perception?",
            dailyAffirmation = "I open my perception to abundance, divine order, and unseen opportunities."
        ),
        Principle(
            id = "principle_4",
            title = "The Law of Non-Resistance & Yielding",
            subtitle = "What you resist persists with magnified charge",
            coreAxiom = "Resistance is attention disguised as hatred; yielding dissolves the energetic knot.",
            deepExplanation = "Fighting a problem gives it your life-force. When you obsessively fear illness, bankruptcy, or rejection, you anchor your consciousness to that precise node in the quantum field. Non-resistance does not mean apathy or surrender to injustice; it means acknowledging 'what is' with radical neutrality so you can intentionally steer toward 'what can be' without emotional friction.",
            scientificSpiritualAnalogy = "Newton's Third Law meets fluid dynamics: For every physical action there is an equal opposite reaction. Water never fights the rock; it flows around it and carves canyons through patience.",
            practicalApplication = "When a stressful interruption occurs today, take one slow breath, unclench your jaw and shoulders, and whisper: 'I accept this moment as it is; I respond from center.'",
            journalPrompt = "What circumstance am I currently fighting with anger or resentment, and what would happen if I dropped the emotional sword?",
            dailyAffirmation = "I release all tension and resistance. I flow harmoniously with the current of life."
        ),
        Principle(
            id = "principle_5",
            title = "The Law of Polarity & Contrast",
            subtitle = "Opposition is the catalyst for deliberate creation",
            coreAxiom = "Contrast is not punishment; it is the friction required to clarify your true desire.",
            deepExplanation = "Without darkness, light cannot be perceived. Without experience of sickness, health has no meaning. When life presents you with difficult contrast, the untrained mind spirals into victimhood. The master recognizes contrast as raw data informing them of what they now choose to align with. Every negative circumstance carries the seed of an equivalent or greater expansion.",
            scientificSpiritualAnalogy = "Electromagnetism: A battery requires both positive and negative poles to generate a current. Spiritual momentum requires contrast to generate deliberate intention.",
            practicalApplication = "Write down the most frustrating problem currently facing you. Directly opposite it, write the exact virtue or clarity that this struggle is demanding you develop.",
            journalPrompt = "How has a past hardship in my life directly sculpted my current strength and wisdom?",
            dailyAffirmation = "Every challenge clarifies my purpose and accelerates my alignment with higher truth."
        ),
        Principle(
            id = "principle_6",
            title = "The Law of Subconscious Sovereignty",
            subtitle = "The subconscious accepts whatever the conscious mind feels as truth",
            coreAxiom = "The subconscious mind does not reason; it obeys emotionalized convictions.",
            deepExplanation = "You cannot trick your subconscious with robotic affirmations spoken over an anxious heart. The subconscious language is feeling-tone and somatic identity. If you repeat 'I am rich' while your chest tightens in panic over a bill, the subconscious registers panic and multiplies it. To reprogram it, you must enter the twilight state (hypnagogic/hypnopompic) where the conscious critical barrier relaxes, and impress feeling-states of completion.",
            scientificSpiritualAnalogy = "Computer firmware: Writing high-level software code (affirmations) cannot fix a corrupted kernel architecture (subconscious childhood conditioning) without entering root administrator mode.",
            practicalApplication = "During the 5 minutes just before falling asleep tonight, loop a short sensory scene that implies your desire is already a settled fact of history.",
            journalPrompt = "What quiet narrative plays in my head when I look in the mirror or look at my bank statement?",
            dailyAffirmation = "My subconscious mind is a fertile garden; I plant only seeds of harmony, wealth, and strength."
        ),
        Principle(
            id = "principle_7",
            title = "The Law of Inspired Action & Detachment",
            subtitle = "Action follows alignment; detachment releases the seed",
            coreAxiom = "Desperation repels; inspired calm opens every doorway.",
            deepExplanation = "There is a massive distinction between 'hustle rooted in terror' and 'inspired action rooted in conviction.' Forced action comes from the ego trying to micro-manage timing and mechanism. Inspired action feels like a natural, joyful next step. Once the action is taken, complete detachment from the outcome is required—just as a farmer does not dig up a planted seed every hour to inspect its roots.",
            scientificSpiritualAnalogy = "Archery: You draw the bow with precision and aim with total focus, but to hit the bullseye, you must cleanly release the arrow without clinging to the string.",
            practicalApplication = "Take one bold, constructive action toward your dream today, then consciously resolve to not worry about the result for the rest of the day.",
            journalPrompt = "Where have I been strangling a goal with overthinking and desperate control?",
            dailyAffirmation = "I act with joy and conviction, then rest in absolute trust of divine timing."
        )
    )

    val chapters = listOf(
        StudyChapter(
            id = 1,
            partNumber = 1,
            partTitle = "Part I: Deconstructing The Illusion",
            chapterTitle = "The Invisible Constants Beneath Reality",
            summary = "Lays the groundwork showing why conventional 'Law of Attraction' techniques fail 95% of practitioners. Introduces the immutable laws operating underneath surface reality, akin to physical laws like gravity.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The Flaw of Toxic Positivity",
                    body = "Owen Hermstring begins by demolishing the myth that forcing a smile or reciting affirmations while boiling with anxiety yields results. The universe does not register linguistic declarations; it operates on the energetic substrate of your felt belief. Repressing negative emotions creates a shadow charge that leaks into your reality as self-sabotage."
                ),
                SectionContent(
                    heading = "The Gravity Analogy",
                    body = "You do not need to pray for gravity to work, nor can you negotiate with it. It is an unyielding constant. Universal laws function identically. When your life is in chaos, it is not because the universe is testing you or being cruel—it is because you are unwittingly aligning with principles that produce friction."
                ),
                SectionContent(
                    heading = "The Mirror of Consciousness",
                    body = "Every human being walks inside a self-projected hall of mirrors. What appears as external enemies, financial droughts, or romantic turmoil are projections of unresolved inner conflicts and accepted limitations."
                )
            ),
            keyTakeaways = listOf(
                "Words without somatic emotion are empty noise to universal law.",
                "Universal constants do not care about your hopes; they respond to your frequency.",
                "Friction in life is feedback, not punishment.",
                "To change the reflection, you must change the origin of the light."
            ),
            reflectionQuestions = listOf(
                "When you practice positive affirmations, what does your stomach and chest actually feel?",
                "What recurring pattern in your relationships or finances proves that a hidden law is operating consistently?",
                "Are you seeking to understand reality, or simply trying to bribe it into giving you comfort?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The Emotional Audit",
                goal = "Uncover the authentic baseline vibration behind your verbal affirmations.",
                steps = listOf(
                    "Sit quietly for 2 minutes with eyes closed.",
                    "State a major life goal aloud (e.g., 'I generate financial abundance').",
                    "Freeze immediately and scan your body: Is there contraction in the throat, chest, or stomach?",
                    "Name that physical contraction honestly (e.g., 'Doubt', 'Fear of failure', 'Guilt').",
                    "Do not fight it; breathe into the sensation until the tension softens."
                ),
                trapToAvoid = "Pretending you feel confident when your nervous system is in fight-or-flight."
            )
        ),
        StudyChapter(
            id = 2,
            partNumber = 1,
            partTitle = "Part I: Deconstructing The Illusion",
            chapterTitle = "The Subconscious Mind: The Silent Programmer",
            summary = "Explores the relationship between the conscious guardian and the vast subconscious ocean. Details how childhood scripts and generational patterns run 95% of daily decisions.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The 95% Autopilot",
                    body = "Cognitive science confirms that up to 95% of our behavioral patterns, micro-expressions, and choices are dictated by the subconscious mind. Your conscious willpower is a tiny rider trying to steer a five-ton subconscious elephant. If the elephant wants to run toward familiar poverty or drama, willpower exhausts itself within days."
                ),
                SectionContent(
                    heading = "The Imprint Period (Ages 0 to 7)",
                    body = "During early childhood, our brains operate in theta brainwave frequencies, absorbing beliefs directly without critical filtering. The phrases your parents spoke about money ('Money doesn't grow on trees', 'Rich people are corrupt') became the hardcoded rules of your personal universe."
                ),
                SectionContent(
                    heading = "The Art of Re-Encoding",
                    body = "The subconscious cannot be reasoned with through debate. It responds to three things: repetition, emotional intensity, and alpha/theta trance states (meditation, the hypnagogic state just before sleep)."
                )
            ),
            keyTakeaways = listOf(
                "Willpower is an exhaustible battery; subconscious programming is a nuclear reactor.",
                "Childhood theta-state imprints dictate your wealth and relationship ceiling.",
                "Reprogramming requires entering relaxed brainwave states where the critical faculty is dormant."
            ),
            reflectionQuestions = listOf(
                "What did you hear your parents or guardians say most frequently about money and success between ages 5 and 10?",
                "In what ways have you recreated the exact emotional atmosphere of your childhood home?",
                "What belief are you ready to evict from your mental operating system?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The Hypnagogic Bridge Exercise",
                goal = "Imprint a new sovereign belief during theta brainwave transition before sleep.",
                steps = listOf(
                    "Lie in bed in a comfortable position, eyes closed.",
                    "Take 5 deep breaths, lengthening each exhalation to slow heart rate.",
                    "As your body becomes heavy and drowsy, evoke a simple 5-second scene implying your new life is true.",
                    "Focus not on the steps, but on the feeling of deep relief and gratitude.",
                    "Loop this short loop continuously until you drift into natural sleep."
                ),
                trapToAvoid = "Making the visualization complex or mentally stressful. Keep it sensory and effortless."
            )
        ),
        StudyChapter(
            id = 3,
            partNumber = 2,
            partTitle = "Part II: The Mechanics of Alignment",
            chapterTitle = "Perception Management vs. Mindset Hustle",
            summary = "Differentiates between superficial 'mindset hustle' (which burns people out) and deep 'perception architecture' that permanently shifts how reality is processed and received.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The Trap of Chronic Hustle",
                    body = "Our culture worships exhaustion as a badge of honor. Hermstring demonstrates that relentless hustle driven by an inner feeling of inadequacy only accelerates burnout. Action taken from lack generates outcomes plagued with fragility and anxiety."
                ),
                SectionContent(
                    heading = "The Reticular Filter Reset",
                    body = "Your Reticular Activating System (RAS) works to validate whatever you believe. If you adopt the perceptual axiom 'There is opportunity everywhere,' your brain literally notices resources, conversations, and synchronicities that were previously invisible to you."
                ),
                SectionContent(
                    heading = "Emotional Equilibrium",
                    body = "High-octane excitement is often just anxiety in reverse. True power resides in emotional equilibrium—a quiet, immovable knowing that doesn't need to scream or frantically overcompensate."
                )
            ),
            keyTakeaways = listOf(
                "Hustle from fear produces fragile success; action from alignment produces compounding ease.",
                "Your RAS filters reality to prove your deepest assumptions correct.",
                "Inner stillness is the highest form of personal leverage."
            ),
            reflectionQuestions = listOf(
                "Do you feel guilty when you rest or do nothing productive?",
                "What opportunities might you be blind to right now because your perceptual filters are set to skepticism?",
                "What would your daily schedule look like if you worked from inspiration rather than fear of lack?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The Reality Filter Calibration",
                goal = "Train your neural filters to actively seek evidence of abundance.",
                steps = listOf(
                    "Set three gentle phone chimes throughout the day.",
                    "When the chime sounds, pause whatever you are doing for 30 seconds.",
                    "Look around your immediate environment and identify 3 subtle signs of order, beauty, or abundance.",
                    "Acknowledge them with genuine internal appreciation.",
                    "Repeat for 7 days to permanently reconfigure your RAS baseline."
                ),
                trapToAvoid = "Skipping the exercise because you are 'too busy working'."
            )
        ),
        StudyChapter(
            id = 4,
            partNumber = 2,
            partTitle = "Part II: The Mechanics of Alignment",
            chapterTitle = "The Energetic Frequency of Wealth & Prosperity",
            summary = "Breaks down money not as physical paper or coins, but as a neutral medium of energetic circulation. Explains how scarcity conditioning acts as an energetic insulator.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "Money as Stored Energy",
                    body = "Money is nothing more than agreed-upon energy that facilitates trade, creativity, and service. When you fear money, hoard it in panic, or resent those who have it, you treat money like radioactive waste. Universal law routes energy to conduits where it can circulate freely and productively."
                ),
                SectionContent(
                    heading = "Clearing the Poverty Vow",
                    body = "Many spiritual and creative seekers harbor an unconscious belief that poverty equals moral purity. Hermstring exposes this as an insidious trap: you cannot be an effective servant of goodness if you are constantly panicked about feeding your family."
                ),
                SectionContent(
                    heading = "Becoming a Conduit, Not a Reservoir",
                    body = "A stagnant pond gathers algae and rot; a mountain stream stays pristine because water enters and departs continuously. When you view yourself as a conduit through which wealth blesses others, your capacity expands exponentially."
                )
            ),
            keyTakeaways = listOf(
                "Money is neutral energetic currency reflecting the value and clarity you project.",
                "Resenting wealthy people locks you permanently into the frequency of lack.",
                "Generosity and joyful circulation dismantle the neurological grip of scarcity."
            ),
            reflectionQuestions = listOf(
                "What is your immediate emotional reaction when you see someone younger than you driving a luxury car?",
                "Do you feel pain or contraction when paying bills, or can you bless the service they provided?",
                "If wealth was guaranteed to flow to you, what daring creative project would you begin tomorrow?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The Bill-Blessing Ritual",
                goal = "Transmute the friction of outgoing money into gratitude and magnetic resonance.",
                steps = listOf(
                    "Take a bill or invoice you currently need to pay.",
                    "Before clicking 'Send' or signing the check, pause and reflect on the value received (electricity, food, shelter, software).",
                    "Mentally say: 'Thank you for this service. I send this money out to circulate and bless all hands it touches.'",
                    "Feel the money multiplying as it leaves your account.",
                    "Complete the payment with a serene smile."
                ),
                trapToAvoid = "Clinging to the money with clenched teeth while paying."
            )
        ),
        StudyChapter(
            id = 5,
            partNumber = 3,
            partTitle = "Part III: The Outer Field of Manifestation",
            chapterTitle = "The Energetic Architecture of Relationships",
            summary = "Demystifies interpersonal dynamics, romantic attraction, and toxic cycles. Demonstrates how your self-concept dictates who enters and stays in your orbit.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The Projection of Self-Concept",
                    body = "Every relationship is a psychological mirror reflecting your unspoken assessment of your own worth. If you subconsciously believe you are unworthy of unconditional love, you will either attract partners who withhold affection, or you will sabotage relationships with partners who are emotionally healthy."
                ),
                SectionContent(
                    heading = "Codependency vs. Interdependence",
                    body = "Needing someone to complete you creates energetic suction and desperation. Two empty vessels clinking together only make hollow noise. Sovereign relationships occur when two whole individuals choose to share their abundance."
                ),
                SectionContent(
                    heading = "Setting Energetic Boundaries",
                    body = "Boundaries are not walls of hostility; they are declarations of self-respect. When you say 'No' to situations that violate your values, you signal to the universe that your energy is sacred."
                )
            ),
            keyTakeaways = listOf(
                "Nobody can treat you consistently worse than you treat yourself in your private thoughts.",
                "Desperation in love repels genuine connection like two identical magnetic poles.",
                "Firm, loving boundaries are the cornerstone of high-frequency relationships."
            ),
            reflectionQuestions = listOf(
                "What toxic trait did your past partners share, and what did that mirror about your own fears?",
                "Are you giving love in order to buy approval and security?",
                "How would you act in your relationships if you had zero fear of being abandoned?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The Sovereign Mirror Dialogue",
                goal = "Heal the root self-concept that attracts relationship friction.",
                steps = listOf(
                    "Stand in front of a mirror in private.",
                    "Look directly into your own eyes without looking away for 60 seconds.",
                    "State firmly: 'I no longer require anyone else to validate my worth. I choose to love and honor you completely.'",
                    "Notice any resistance or tears that arise; permit them to wash through you.",
                    "Breathe in deep self-acceptance."
                ),
                trapToAvoid = "Criticizing your physical appearance in the mirror instead of seeing your essence."
            )
        ),
        StudyChapter(
            id = 6,
            partNumber = 3,
            partTitle = "Part III: The Outer Field of Manifestation",
            chapterTitle = "Dismantling Root Resistance & Self-Sabotage",
            summary = "Analyzes the psychological and energetic mechanisms of why people ruin their own success right at the threshold of victory.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The Upper Limit Problem",
                    body = "Every human has an internal 'thermostat' for how much joy, wealth, and love they feel safe experiencing. When life gets 'too good,' our primitive nervous system perceives an existential threat of losing belonging or courting envy, triggering self-destructive behavior."
                ),
                SectionContent(
                    heading = "Secondary Gains",
                    body = "Illness, chronic drama, and financial struggle often carry hidden 'secondary gains'—they earn you sympathy, provide an excuse for not taking risks, or keep you connected to family traditions of suffering. Until you surrender the secondary gain, the problem cannot be dissolved."
                ),
                SectionContent(
                    heading = "Expanding Somatic Capacity",
                    body = "You can only hold what your nervous system can regulate. True manifestation requires training your body to tolerate greater and greater quantities of calm, ease, and blessing without manufactured drama."
                )
            ),
            keyTakeaways = listOf(
                "Self-sabotage is a misguided defense mechanism against unfamiliar expansion.",
                "Identify what hidden payoff or sympathy you receive from staying in struggle.",
                "Expand your nervous system's capacity for peace and success."
            ),
            reflectionQuestions = listOf(
                "What disastrous thing do you unconsciously fear will happen if you become wildly successful and happy?",
                "Who in your life would be made uncomfortable if you achieved total financial freedom?",
                "What excuses would you lose if your biggest problem vanished today?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "Upper Limit Thermostat Reset",
                goal = "Condition your somatic nervous system to sustain elevated states of joy and peace.",
                steps = listOf(
                    "Recall a moment of deep pride, joy, or satisfaction.",
                    "Feel the warm sensation expand in your torso.",
                    "Instead of cutting it short with a worry thought, consciously stay in that sensation for 3 uninterrupted minutes.",
                    "Breathe slowly, signaling to your amygdala: 'This is safe. I am permitted to thrive.'",
                    "Open your eyes with gratitude."
                ),
                trapToAvoid = "Immediately reaching for your phone or searching for a problem to solve."
            )
        ),
        StudyChapter(
            id = 7,
            partNumber = 4,
            partTitle = "Part IV: Sovereign Mastery & Daily Practice",
            chapterTitle = "The Protocol of Intentional Living",
            summary = "Synthesizes all universal laws into an actionable daily operating rhythm. How to live as a conscious creator rather than a reactionary consumer.",
            deepDiveSections = listOf(
                SectionContent(
                    heading = "The Morning Sovereignty Hour",
                    body = "How you spend the first 30 minutes after waking sets your vibration for the entire day. Checking news or social media instantly throws your mind into reactive, beta-wave distress. Hermstring prescribes sacred morning anchoring: hydration, breathwork, silence, and conscious assumption."
                ),
                SectionContent(
                    heading = "Navigating The Delay Interval",
                    body = "Physical reality has density and momentum; there is an inevitable gestation period between energetic alignment and physical manifestation. The amateur assumes nothing is happening and gives up. The master understands that the seed is germinating underground."
                ),
                SectionContent(
                    heading = "Living from the Wish Fulfilled",
                    body = "Do not live *towards* your dream; live *from* it. Ask yourself: 'How does someone who already possesses this peace, this wealth, and this love walk, speak, and treat people today?' Embody that version of yourself now."
                )
            ),
            keyTakeaways = listOf(
                "Protect the first 30 minutes of your morning with your life.",
                "The gestation period is testing your steadfastness, not denying your request.",
                "Live *from* the state of wish fulfilled, not *toward* it."
            ),
            reflectionQuestions = listOf(
                "What is the first thing your hand touches when you wake up in the morning?",
                "How do you handle the silence between planting your intention and seeing the harvest?",
                "Who must you become today to match the reality you claim you want tomorrow?"
            ),
            alignmentExercise = AlignmentExercise(
                title = "The 24-Hour Master Alignment Protocol",
                goal = "Integrate intentional living throughout morning, afternoon, and evening.",
                steps = listOf(
                    "Morning: No screens for first 20 minutes. Drink water, sit in 5 minutes of stillness, invoke the state of completion.",
                    "Midday: 60-second non-resistance reset whenever tension arises.",
                    "Evening: 5-minute review of blessings, followed by the Hypnagogic Bridge as you fall asleep.",
                    "Maintain this cadence for 21 days for permanent neurological restructuring."
                ),
                trapToAvoid = "Treating the protocol as a checklist to rush through rather than a sanctuary of being."
            )
        )
    )

    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            question = "According to 'The Law Behind The Laws', why do traditional positive affirmations often fail?",
            options = listOf(
                "They are not spoken loudly enough to impact the universe.",
                "The words conflict with the deeper somatic felt-belief stored in the subconscious.",
                "The universe only responds to people who write them down 55 times.",
                "Affirmations only work during certain planetary alignments."
            ),
            correctIndex = 1,
            explanation = "The subconscious and quantum field respond to felt vibration, not verbal statements. If you speak words of wealth while feeling terror in your gut, the terror is the broadcast that gets answered."
        ),
        QuizQuestion(
            id = 2,
            question = "What does Hermstring mean by the 'Gravity Analogy' of universal laws?",
            options = listOf(
                "You must constantly beg gravity to hold you down.",
                "Universal laws only apply if you join a spiritual organization.",
                "Universal laws are immutable constants that operate automatically regardless of belief or emotion.",
                "Gravity can be switched off through extreme willpower."
            ),
            correctIndex = 2,
            explanation = "Universal laws operate mathematically and neutrally like gravity. They are not judging you; they are simply returning the exact physical equivalent of your internal frequency."
        ),
        QuizQuestion(
            id = 3,
            question = "Which brainwave state during early childhood (ages 0-7) causes core beliefs to be hardcoded without filtering?",
            options = listOf(
                "Beta brainwaves (high alertness)",
                "Gamma brainwaves (intense problem solving)",
                "Theta brainwaves (deep hypnosis and suggestibility)",
                "High-frequency supersonic brainwaves"
            ),
            correctIndex = 2,
            explanation = "Children between 0 and 7 operate primarily in hypnagogic theta states, absorbing everything their parents say and do directly into the subconscious operating system."
        ),
        QuizQuestion(
            id = 4,
            question = "What is the primary difference between 'hustle from lack' and 'inspired action'?",
            options = listOf(
                "Inspired action requires working at least 16 hours a day.",
                "Hustle from lack is fueled by anxiety and fear of ruin; inspired action stems from grounded certainty and joyful alignment.",
                "Inspired action means doing absolutely nothing and waiting for a check in the mail.",
                "There is no difference; all action is identical."
            ),
            correctIndex = 1,
            explanation = "The energetic seed of an action determines its fruit. Frantic hustling to escape poverty creates fragile outcomes burdened with burnout, whereas aligned action flows from inner conviction."
        ),
        QuizQuestion(
            id = 5,
            question = "What is the 'Upper Limit Problem' described in Chapter 6?",
            options = listOf(
                "A legal ceiling on how much money a bank can store.",
                "An internal subconscious thermostat that triggers self-sabotage when life feels unfamiliar or 'too good.'",
                "A limitation caused by physical exhaustion after age 50.",
                "The maximum number of affirmations a person can speak in a day."
            ),
            correctIndex = 1,
            explanation = "When you exceed your conditioned baseline of happiness or wealth, your nervous system interprets unfamiliar joy as danger, frequently triggering fights, carelessness, or illness to pull you back to the familiar struggle zone."
        ),
        QuizQuestion(
            id = 6,
            question = "How should one navigate the 'Delay Interval' (the time between setting an intention and physical harvest)?",
            options = listOf(
                "Panic and change goals every three days.",
                "Dig up the seed continuously to check if roots are growing.",
                "Rest in steadfast knowing that physical matter has density and require gestation time to reorganize.",
                "Pay someone else to manifest it for you."
            ),
            correctIndex = 2,
            explanation = "Physical reality has momentum. The delay interval is not a rejection; it is the natural gestation period required for quantum potentials to assemble physically."
        ),
        QuizQuestion(
            id = 7,
            question = "What does it mean to live 'from the wish fulfilled' rather than 'toward' it?",
            options = listOf(
                "Spending all your money today so you have zero dollars left.",
                "Pretending reality doesn't exist and hallucinating.",
                "Adopting the emotional posture, calm speech, and noble choices of the version of you who already has the outcome.",
                "Waiting until you get the prize before feeling any gratitude."
            ),
            correctIndex = 2,
            explanation = "When you inhabit the emotional certainty and posture of completion now, your perception and actions naturally synchronize with that reality, pulling it into physical form."
        )
    )
}
